package com.deco.user;

import java.util.Random;

public class RandomCode {
	/**
	  @param 원하는 랜덤 자리수
	  @return Random Code
	 **/
	public static String getCode(int len){
		String code = "";
		Random oneNum = new Random();
		
		for(int i = 0; i < len; i++){
			code += Integer.toString(oneNum.nextInt(10));
		}
		
		return code;
	}
}
