package com.mywjdh.logistics.logisticswechat.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

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
}
