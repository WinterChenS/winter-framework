package com.winterchen.common.utils.encrypt;


import com.winterchen.common.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


/**
 * DES对称加解密
 * 
 * @author huajiejun
 * @version 创建时间：2017年5月25日 下午7:10:17
 */
@Slf4j
public class DESUtil {
	static final String KEY_ALGORITHM = "DES";
	static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	static final String DEFAULT_KEY = "am95YXJkLmNvbQ==";
	
	/**
	 * 获取密钥
	 * 
	 * @return 密钥
	 * @throws NoSuchAlgorithmException
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午8:57:57
	 */
	public static String initkey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); // 实例化密钥生成器
		kg.init(56); // 初始化密钥生成器
		SecretKey secretKey = kg.generateKey(); // 生成密钥
		return Base64Util.encode(secretKey.getEncoded()); // 获取二进制密钥编码形式
	}
	
	/**
	 * 加密，使用默认密钥对字符串进行加密
	 * 
	 * @param data 待加密字符串
	 * @return 密文
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午8:58:21
	 */
	public static String encrypt(String data){
		return encrypt(data,DEFAULT_KEY);
	}
	
	/**
	 * 加密
	 * 
	 * @param data 待加密字符串
	 * @param key 密钥
	 * @return 密文
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午8:59:01
	 */
	public static String encrypt(String data, String key) {
		try{
			Key k = toKey(Base64Util.decode(key)); // 还原密钥
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // 实例化Cipher对象，它用于完成实际的加密操作
			cipher.init(Cipher.ENCRYPT_MODE, k); // 初始化Cipher对象，设置为加密模式
			return Base64Util.encode(cipher.doFinal(data.getBytes())); // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
		}catch (Exception e) {
			log.info("加密报错",e);
		}
		
		return "";
	}
	
	/**
	 * 解密，使用默认密钥进行解密
	 * 
	 * @param data 待解密字符串
	 * @return 解密后字符串
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午8:59:43
	 */
	public static String decrypt(String data) {
		return decrypt(data, DEFAULT_KEY);
	}

	/**
	 * 解密
	 * 
	 * @param data 待解密字符串
	 * @param key 密钥
	 * @return 解密后字符串
	 * @return: String
	 * @author: huajiejun
	 * @version 创建时间：2017年5月25日 下午9:01:23
	 */
	public static String decrypt(String data, String key) {
		try{
			Key k = toKey(Base64Util.decode(key));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, k); // 初始化Cipher对象，设置为解密模式
			return new String(cipher.doFinal(Base64Util.decode(data))); // 执行解密操作
		}catch (Exception e) {
			log.info("解密报错",e);
		}

		return "";
	}

	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key); // 实例化Des密钥
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM); // 实例化密钥工厂
		SecretKey secretKey = keyFactory.generateSecret(dks); // 生成密钥
		return secretKey;
	}
}
