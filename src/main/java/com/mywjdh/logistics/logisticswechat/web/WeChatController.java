package com.mywjdh.logistics.logisticswechat.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sword.wechat4j.user.UserManager;

import com.mywjdh.logistics.logisticswechat.domain.User;
import com.mywjdh.logistics.logisticswechat.service.UserService;
import com.mywjdh.logistics.logisticswechat.util.SMSUtil;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
	private static Logger log = LoggerFactory.getLogger(WeChatController.class);
	@Autowired
	private UserService userService;
	@RequestMapping
	public void execute(HttpServletRequest request, HttpServletResponse response){
		Wechat wechat = new Wechat(request);
		String result = wechat.execute();
		try {
			ServletOutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("init")
	public String init(HttpServletRequest request, HttpServletResponse response){
	    String code = request.getParameter("code");  
	    UserManager userManager = new UserManager();
		String openid = userManager.getOpenId(code);
		User user = userService.findUserByOpenid(openid);
		HttpSession session = request.getSession();
		if(StringUtils.isEmpty(user)){
			log.info("通过openid:[{}]查找用户信息不存在.",openid);
			session.setAttribute("openid", openid);
			return "login";
		}else{
			log.info("通过openid:[{}]查找用户信息已存在,跳到管理页面",openid);
			return "manager";
		}
	}
	@RequestMapping("sendMsg")
	@ResponseBody
	public Map<String,String> sendMsg(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String mobile = request.getParameter("mobile");
		String code = SMSUtil.sendMsg(mobile);
		log.info("测试模式,code[{}]",code);
		session.setAttribute("validateCode", mobile.concat(code));
		Map<String,String> map = new HashMap<String,String>();
		map.put("responseCode", "00000000");
		map.put("responseMsg", "操作成功");
		return map;
	}
	
	@RequestMapping("validateCode")
	@ResponseBody
	public Map<String,String> validateCode(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String mobile = request.getParameter("mobile");
		String validateCode = request.getParameter("validateCode");
		validateCode = mobile.concat(validateCode);
		String oldValidateCode = (String)session.getAttribute("validateCode");
		if(!validateCode.equals(oldValidateCode)){
			map.put("responseCode", "99999999");
			map.put("responseMsg", "操作失败");
			return map;
		}
		map.put("responseCode", "00000000");
		map.put("responseMsg", "操作成功");
		return map;
	}
	
	@RequestMapping("bindUser")
	@ResponseBody
	public Map<String,String> bindUser(HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String openId = (String) session.getAttribute("openid");
		UserManager userManager = new UserManager();
		org.sword.wechat4j.user.User wechatUser = userManager.getUserInfo(openId);

		String usertype = request.getParameter("usertype");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String company = request.getParameter("company");
		String name = wechatUser.getNickName();
		
		String validateCode = request.getParameter("validateCode");
		validateCode = mobile.concat(validateCode);
		String oldValidateCode = (String)session.getAttribute("validateCode");
		if(validateCode.equals(oldValidateCode)){
			User record = new User();
			record.setUsername(mobile);
			record.setPassword(password);
			record.setNo(openId);
			record.setName(name);
			record.setUserType(usertype);
			record.setCompanyName(company);
			record.setCreateDate(new Date());
			record.setUpdateDate(new Date());
			userService.insert(record);
			session.invalidate();
		}
		map.put("responseCode", "00000000");
		map.put("responseMsg", "操作成功");
		return map;
	}
	
}
