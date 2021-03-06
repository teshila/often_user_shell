package com.ly.placeholder;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {
	private static Key key;
	private static String KEY_STR = "ynrtml";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncryptString(String str) {
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byte[] strBytes = str.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64en.encode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES解密
	 * 
	 * @param str
	 * @return
	 */
	public static String getDecryptString(String str) {
		BASE64Decoder base64De = new BASE64Decoder();
		try {
			byte[] strBytes = base64De.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws Exception {
		/*if (args == null || args.length < 1) {
			System.out.println("请输入要加密的字符，用空格分隔.");
		} else {
			for (String arg : args) {
				System.out.println(arg + ":" + getEncryptString(arg));
			}    
		}*/
		System.out.print(getEncryptString("N_C75B3CA746CBC96BFFF88D4A3A16AF03125A3A12A6A45D5B1DED34B1D02572D70A05E33401B89DE26E69F4635307F8BFF9CEC0BCB46D0E1D48E82CC9AFDF9480BAE22BDC8720F826"));
		//System.out.println(getDecryptString("WnplV/ietfQ="));
		//System.out.println(getDecryptString("gJQ9O+q34qk="));
		System.out.println();
		System.out.println();
		//System.out.println(getDecryptString("quSMvNhk9K1dzzn5Qg6kanXaMzJqNJKPaE8yH/iVFXS1kHDx7v6LZ9YlgjlGr9Kd28zcAhH3Ezg1ie66jZZIi6SxLchzvXmPjAlOCwYwrQuyVb+zdOXYNIjcz/gWzbZ6ORU+8TSNp59zQdjcoVrPfhd/RzJbYAQZ+567iSSG9ujsPl75F1FSkhhL6IoMI0kA77QhebIV5gQ="));
	}
}
