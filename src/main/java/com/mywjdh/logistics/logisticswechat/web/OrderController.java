package com.mywjdh.logistics.logisticswechat.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mywjdh.logistics.logisticswechat.domain.Order;
import com.mywjdh.logistics.logisticswechat.service.OrderService;
import com.mywjdh.logistics.logisticswechat.util.MailUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	private static Logger log = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
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
	@RequestMapping("buy")
	public String buy(HttpServletRequest request, HttpServletResponse response){
	    return "buy";
	}
	
	
	@RequestMapping("create")
	@ResponseBody
	public Map<String, String> create(Order record,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		if(StringUtils.isEmpty(userId)){
			response.sendRedirect("../user/init");
		}
		log.info("准备创建订单,订单信息:{}",record);
		
		record.setUserId(userId);
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		record.setStatus("1");
		record.setDelFlag("1");
		orderService.insert(record);
		try {
			String from = "pplp@163.com";
			String password = "ericsson521a";

			String[] to = { "773152@qq.com" };
			String subject = "在线买单成功";
			String body = record.toString();
			MailUtil mail = new MailUtil(from, password, to, RecipientType.TO, subject, body);
			mail.setMailBody("你好，我是正文"); // 设置正文

			mail.send();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("responseCode", "00000000");
		map.put("responseMsg", "操作成功");
		return map;
	}
	
	
	@RequestMapping("order")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response){
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		
		List<Order> orders = orderService.selectByUserId(userId);
		for(Order order:orders){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date2 = sdf.format(date);
			if (!jisuan(order.getReqTime(), date2) && !order.getStatus().equals("9")){
				order.setStatus("9");
				orderService.updateByPrimaryKeySelective(order);
			}
		}
		orders = orderService.selectByUserId(userId);
		ModelAndView modelAndView = new ModelAndView("order");
		modelAndView.addObject("orders", orders);
		return modelAndView;
	}
	
	 public static boolean jisuan(String date1, String date2)  { 
		 if(StringUtils.isEmpty(date1)||StringUtils.isEmpty(date2)){
			 return true;
		 }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss"); 
	        Date start;
			try {
				start = sdf.parse(date1);
				java.util.Date end = sdf.parse(date2); 
		        long cha = end.getTime() - start.getTime(); 
		        double result = cha * 1.0 / (1000 * 60 * 60); 
		        if(result<=24){ 
		             //System.out.println("可用");   
		             return true; 
		        }else{ 
		             //System.out.println("已过期");  
		             return false; 
		        } 
			} catch (ParseException e) {
				
				e.printStackTrace();
			} 
	        return true;
	    } 
	
}
