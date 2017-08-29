package com.mywjdh.logistics.logisticswechat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mywjdh.logistics.logisticswechat.aop.Token;

@Controller
public class TokenController {
	@Token(save = true)
	@RequestMapping("/savetoken")
	@ResponseBody
	public String getToken(HttpServletRequest request, HttpServletResponse response) {
		return (String) request.getSession().getAttribute("token");
	}

	@Token(remove = true)
	@RequestMapping("/removetoken")
	@ResponseBody
	public String removeToken(HttpServletRequest request, HttpServletResponse response) {
		return "success";
	}
}
