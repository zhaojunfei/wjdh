package com.mywjdh.logistics.logisticswechat.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/")
	public String login() {
		return "login";
	}
}