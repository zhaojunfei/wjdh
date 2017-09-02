package com.mywjdh.logistics.logisticswechat;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.nfunk.jep.JEP;

public class App {

	private final static String[] rets = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public double operation(String exp,Double... values){
		JEP jep = new JEP();
		for(int i=0;i<values.length;i++){
			jep.addVariable(rets[i], values[i]);
		}
		jep.parseExpression(exp);
		double complex = jep.getValue();
		return complex;
	}
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		String[] rets = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		JEP jep = new JEP(); // 一个数学表达式
		String exp = "F208+9"; // 给变量赋值
		jep.addVariable("a", 8.00004);
		jep.addVariable("b", 10);
		jep.addVariable("c", 19);
		jep.parseExpression(exp);
		double complex = jep.getValue();
		System.out.println(complex);
		Operation Oper = new Operation();
		Oper.setF208(10);
		Oper.setF111(10);
		double r = operation(exp,Oper);
		System.out.println(r);
		
		
	}
	
	
	public static double operation(String exp,Oper o){
		Class clazz = (Class) o.getClass();
		JEP jep = new JEP(); // 一个数学表达式
		try {
	       Field[] fs = clazz.getDeclaredFields();  
	       for(int i = 0 ; i < fs.length; i++){  
	           Field f = fs[i];  
	           f.setAccessible(true);
	           String key = f.getName();
	           Object val = f.get(o);
	           String type = f.getType().toString();
	           if(type.endsWith("String") ){
	        	   jep.addVariable(key, (Double)val);
	           }else if(type.endsWith("double") || type.endsWith("Double")){
	        	   jep.addVariable(key, (Double)val);
	           }else if(type.endsWith("BigDecimal")){
	        	   BigDecimal r = (BigDecimal)val;
	        	   jep.addVariable(key, r.doubleValue());
	           }else{
	        	   jep.addVariable(key, (Double)val);
	           }
	       }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		jep.parseExpression(exp);
		double complex = jep.getValue();
		return complex;
	} 
	
}
