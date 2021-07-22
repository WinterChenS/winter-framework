package com.winterchen.common.utils.encrypt;



import com.winterchen.common.utils.Base64Util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;


/**
 * 三重DES加密工具类
 * <p>1975年美国IBM公司成功研究并发布了DES加密算法，但DES密码长度容易被暴力破解，通过对DES算法进行改进，针对每个数据块进行三次DES加密，也就是3DES加密算法</p>
 * 
 *@author huajiejun
 * @version 创建时间：2017年5月25日 下午9:52:55
 */
public class DES3Util {
	// 密钥 （24位）
	private static final String SECRET_KEY = "ishangzuishangzuishangzu";
	// 向量 （8位）
	private static final String IV = "ishzishz";
	// 加解密统一使用的编码方式
	private static final String DEFAULT_CHARSET = "utf-8";

	/**
	 * 3DES加密 ，使用默认密钥和默认向量加密
	 * 
	 * @param plainText 待加密字符串
	 * @return 密文
	 * @throws Exception
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午9:56:48
	 */
	public static String encrypt(String plainText) throws Exception {
		return encrypt(plainText,SECRET_KEY,IV);
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText 待加密字符串
	 * @param secretKey 密钥 （24位）
	 * @param iv 向量 （8位）
	 * @return 密文
	 * @throws Exception
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午10:02:00
	 */
	public static String encrypt(String plainText, String secretKey, String iv) throws Exception {
		if (null == secretKey || secretKey.length() != 24) {
			throw new IllegalArgumentException("密钥必须等于24位");
		}

		if (null == iv || iv.length() != 8) {
			throw new IllegalArgumentException("向量必须等于8位");
		}

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(DEFAULT_CHARSET));
		return Base64Util.encode(encryptData);
	}
	
	/**
	 * 3DES解密
	 * 
	 * @param encryptText 密文
	 * @return 解密后字符串
	 * @throws Exception
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午10:03:26
	 */
	public static String decrypt(String encryptText) throws Exception {
		return decrypt(encryptText,SECRET_KEY,IV);
	}
	
	
	/**
	 * 3DES解密
	 * 
	 * @param encryptText 密文
	 * @param secretKey 密钥 （24位）
	 * @param iv 向量 （8位）
	 * @return 解密后字符串
	 * @throws Exception
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午10:04:36
	 */
	public static String decrypt(String encryptText, String secretKey, String iv) throws Exception {
		if (null == secretKey || secretKey.length() != 24) {
			throw new IllegalArgumentException("密钥必须等于24位");
		}

		if (null == iv || iv.length() != 8) {
			throw new IllegalArgumentException("向量必须等于8位");
		}
		
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64Util.decode(encryptText));
		return new String(decryptData, DEFAULT_CHARSET);
	}
}
