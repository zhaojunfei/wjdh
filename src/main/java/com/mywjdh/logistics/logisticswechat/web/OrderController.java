package com.mywjdh.logistics.logisticswechat.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mywjdh.logistics.logisticswechat.domain.Order;
import com.mywjdh.logistics.logisticswechat.service.OrderService;

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
	public Map<String, String> create(Order record,HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		//Integer userId = (Integer)request.getSession().getAttribute("userid");
		log.info("准备创建订单,订单信息:{}",record);
		int  userId = 1;
		record.setUserId(userId);
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		record.setStatus("1");
		record.setDelFlag("1");
		orderService.insert(record);
		map.put("responseCode", "00000000");
		map.put("responseMsg", "操作成功");
		return map;
	}
	
	
	@RequestMapping("order")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response){
		//Integer userId = (Integer)request.getSession().getAttribute("userid");
		int  userId = 1;
		List<Order> orders = orderService.selectByUserId(userId);
		ModelAndView modelAndView = new ModelAndView("order");
		modelAndView.addObject("orders", orders);
		return modelAndView;
	}
	
}
