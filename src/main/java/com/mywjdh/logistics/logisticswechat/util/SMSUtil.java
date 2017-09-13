package com.mywjdh.logistics.logisticswechat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.chuanglan.sms.request.SmsSendRequest;
import com.chuanglan.sms.response.SmsSendResponse;
import com.chuanglan.sms.util.ChuangLanSmsUtil;

public class SMSUtil {

	public static final String DEF_CHATSET = "UTF-8";

	public static final int DEF_CONN_TIMEOUT = 30000;

	public static final int DEF_READ_TIMEOUT = 30000;

	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	
	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "N5676872";
	// 用户平台API密码(非登录密码)
	public static String pswd = "erduiCESHI17";
	
	// 2.发送短信

	public static String aa(String mobile) {
		
		String url = "http://send.18sms.com/msg/HttpBatchSendSM";
		Map<String,String> params = new HashMap<String,String> ();
		params.put("account", "my15957199227");
		params.put("pswd", "M78aTe23");
		params.put("mobile", mobile);
		String msg = "您的验证码是：168168。"+
"感谢您注册示远科技会员，为了您的安全请及时去更换您的初始密码，如有疑问请联系：4007761818。"+
"感谢您的注册示远科技会员，验证码{code}，请及时输入验证码进行登陆。"+
"您的帐号密码为：4007761818，请查收，详情客服电话4007761818。"+
"您的手机号：18858103840验证码888168，一天内提交有效，如不是本人操作请忽略此短信。"+
"您好，您的验证码888168，5分钟内有效。";
		String code = autoRandomCode(6);
		msg = msg.replace("{code}", code);
		params.put("msg", msg);
		params.put("needstatus", "true");
		params.put("product", "9418211");
		try {
			String result = net(url, params, "POST");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return code;

	}
	
	public static String sendMsg(String mobile) {

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsSingleRequestServerUrl = " http://smssh1.253.com/msg/send/json";
		// 短信内容
		String msg = "您的验证码是：{code}。"+
				"感谢您注册示远科技会员，为了您的安全请及时去更换您的初始密码，如有疑问请联系：4007761818。"+
				"感谢您的注册示远科技会员，验证码{code}，请及时输入验证码进行登陆。"+
				"您的帐号密码为：4007761818，请查收，详情客服电话4007761818。"+
				"您的手机号：18858103840验证码888168，一天内提交有效，如不是本人操作请忽略此短信。"+
				"您好，您的验证码888168，5分钟内有效。";
		String code = autoRandomCode(6);
		msg = msg.replace("{code}", code);
		//手机号码
		String phone = mobile;
		//状态报告
		String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		
		
		String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		return code;

	}
	
	
	public static String autoRandomCode(int length){
		Random  d = new Random();
		String str = "";
		for (int i = 0; i < length; i++) {
			int num = d.nextInt(10);
			str += num + "";
		}
		return str;
	}
	
	public static void main(String[] args) {
		sendMsg("15321150102");
	}

	/**
	 *
	 * 
	 * 
	 * @param strUrl
	 *            请求地址
	 * 
	 * @param params
	 *            请求参数
	 * 
	 * @param method
	 *            请求方法
	 * 
	 * @return 网络请求字符串
	 * 
	 * @throws Exception
	 * 
	 */

	public static String net(String strUrl, Map params, String method) throws Exception {

		HttpURLConnection conn = null;

		BufferedReader reader = null;

		String rs = null;

		try {

			StringBuffer sb = new StringBuffer();

			if (method == null || method.equals("GET")) {

				strUrl = strUrl + "?" + urlencode(params);

			}

			URL url = new URL(strUrl);

			conn = (HttpURLConnection) url.openConnection();

			if (method == null || method.equals("GET")) {

				conn.setRequestMethod("GET");

			} else {

				conn.setRequestMethod("POST");

				conn.setDoOutput(true);

			}

			conn.setRequestProperty("User-agent", userAgent);

			conn.setUseCaches(false);

			conn.setConnectTimeout(DEF_CONN_TIMEOUT);

			conn.setReadTimeout(DEF_READ_TIMEOUT);

			conn.setInstanceFollowRedirects(false);

			conn.connect();

			if (params != null && method.equals("POST")) {

				try {

					DataOutputStream out = new DataOutputStream(conn.getOutputStream());

					out.writeBytes(urlencode(params));

				} catch (Exception e) {

					// TODO: handle exception

				}

			}

			InputStream is = conn.getInputStream();

			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));

			String strRead = null;

			while ((strRead = reader.readLine()) != null) {

				sb.append(strRead);

			}

			rs = sb.toString();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (reader != null) {

				reader.close();

			}

			if (conn != null) {

				conn.disconnect();

			}

		}

		return rs;

	}

	// 将map型转为请求参数型

	public static String urlencode(Map<String, Object> data) {

		StringBuilder sb = new StringBuilder();

		for (Map.Entry i : data.entrySet()) {

			try {

				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}

}