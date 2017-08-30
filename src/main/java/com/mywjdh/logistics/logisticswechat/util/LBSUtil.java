package com.mywjdh.logistics.logisticswechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LBSUtil {
	private static final double EARTH_RADIUS = 6378137;// 赤道半径(单位m)

	/**
	 * 转化为弧度(rad)
	 */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 基于余弦定理求两经纬度距离
	 * 
	 * @param lon1
	 *            第一点的精度
	 * @param lat1
	 *            第一点的纬度
	 * @param lon2
	 *            第二点的精度
	 * @param lat3
	 *            第二点的纬度
	 * @return 返回的距离，单位km
	 */
	public static double lantitudeLongitudeDist(double lon1, double lat1, double lon2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);

		double radLon1 = rad(lon1);
		double radLon2 = rad(lon2);

		if (radLat1 < 0)
			radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
		if (radLat1 > 0)
			radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
		if (radLon1 < 0)
			radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
		if (radLat2 < 0)
			radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
		if (radLat2 > 0)
			radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
		if (radLon2 < 0)
			radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
		double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
		double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
		double z1 = EARTH_RADIUS * Math.cos(radLat1);

		double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
		double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
		double z2 = EARTH_RADIUS * Math.cos(radLat2);

		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
		// 余弦定理求夹角
		double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d)
				/ (2 * EARTH_RADIUS * EARTH_RADIUS));
		double dist = theta * EARTH_RADIUS;
		return dist;
	}

	public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
		String address = "";
		String lat = "";
		String lng = "";
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format(
				"http://api.map.baidu.com/geocoder/v2/?" + "ak=eb0MzZ7c5u3kdZBNugkxsyDxqdummkkF&output=json&address=%s",
				address);
		URL myURL = null;
		URLConnection httpsConn = null;
		// 进行转码
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {

		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(),
							data.indexOf("},\"precise\""));
					lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(), data.indexOf(",\"lat\""));
				}
				insr.close();
			}
		} catch (IOException e) {

		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lat", new BigDecimal(lat));
		map.put("lng", new BigDecimal(lng));
		return map;
	}
	
	public static void main(String[] args){
		Map<String,BigDecimal> bb = getLatAndLngByAddress("北京市海淀区信息路3号");
		Map<String,BigDecimal> jj = getLatAndLngByAddress("龙域中路5号");
		
		System.out.println(jj);
		Double lon1 = bb.get("lng").doubleValue();
		Double lat1 = bb.get("lat").doubleValue();
		Double lon2 = jj.get("lng").doubleValue();
		Double lat2 = jj.get("lat").doubleValue();
		double cc = lantitudeLongitudeDist(lon1, lat1, lon2, lat2);
		System.out.println(cc);
	}
}
