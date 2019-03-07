package com.mum.groupproject.survey.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	public static String md5(String msg) {
		String encMsg = "";
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
		md5.update(msg.getBytes());
		for (int b : md5.digest()) {
			b = b & 0xFF;
			if (b < 16) {
				encMsg += "0";
			}
			encMsg += Integer.toString(b, 16).toUpperCase();
		}
		return encMsg;
	}

}
