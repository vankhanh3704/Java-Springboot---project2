package com.javaweb.utils;


// dùng để check các điều kiện 
public class StringUtil {
	public static boolean checkString(String data) {
		if(data != null && !data.equals("")) {
			return true;
		}
		return false;
	}
}
