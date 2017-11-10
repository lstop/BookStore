package com.lstop.utils;

import java.math.BigInteger;
import java.security.MessageDigest;


public class MD5Utils {

	public static String pwdByMD5(String pwd) {
		String pwdResult="";
		try {
			//1.创建对象MessageDigest
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			//2.调用digest()方法进行md5算法运算
			byte[]  bs = messageDigest.digest(pwd.getBytes("utf-8"));
			//把字节中的内容转化16进制的字符串
			pwdResult  =  new BigInteger(1, bs).toString(16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwdResult;
	}
}
