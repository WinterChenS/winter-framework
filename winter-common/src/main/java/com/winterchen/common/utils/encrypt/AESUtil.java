package com.winterchen.common.utils.encrypt;



import com.winterchen.common.utils.Base64Util;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;


/**
 * AES对称加解密
 * <p>高级加密标准（英语：Advanced Encryption Standard，缩写：AES），是一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。</p>
 * 
 */
public class AESUtil {
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
//	@SuppressWarnings("unused")
//	private static String binary(byte[] bytes, int radix){  
//        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
//    }  
      
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    private static String base64Encode(byte[] bytes){  
        return Base64Util.encode(bytes);
    }  
      
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
    private static byte[] base64Decode(String base64Code) throws Exception{  
        return Base64Util.decode(base64Code);
    }  
      
    /** 
     * 获取byte[]的md5值 
     * @param bytes byte[] 
     * @return md5 
     * @throws Exception 
     */  
    private static byte[] md5(byte[] bytes) throws Exception {  
        MessageDigest md = MessageDigest.getInstance("MD5");  
        md.update(bytes);  
          
        return md.digest();  
    }  
      
    /** 
     * 获取字符串md5值 
     * @param msg  
     * @return md5 
     * @throws Exception 
     */  
    private static byte[] md5(String msg) throws Exception {  
        return md5(msg.getBytes());  
    }  
      
    /** 
     * 结合base64实现md5加密 
     * @param msg 待加密字符串 
     * @return 获取md5后转为base64 
     * @throws Exception 
     */  
	public static String md5Encrypt(String msg) throws Exception{  
        return StringUtils.isBlank(msg) ? null : base64Encode(md5(msg));
    }  

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     * @return: byte[]
     * @author huajiejun
     * @version 创建时间：2017年5月25日 下午10:21:54
     */
    public static byte[] encryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));  
  
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
          
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
      
    /**
     * AES加密，密文为base64 code
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥
     * @return 加密后的base64 code
     * @throws Exception
     * @return: String
     * @author huajiejun
     * @version 创建时间：2017年5月25日 下午10:21:12
     */
    public static String encrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(encryptToBytes(content, encryptKey));  
    }  
        
    /**
     * AES解密
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception
     * @return: String
     * @author huajiejun
     * @version 创建时间：2017年5月25日 下午10:20:40
     */
    public static String decryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
    	if(encryptBytes==null){
    		return null;
    	}
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));  
          
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes);  
    }
    
    /**
     * AES解密，将base64 code AES解密
     * @param encryptStr 待解密的base64 code
     * @param decryptKey 解密密钥 
     * @return 解密后的string
     * @throws Exception
     * @return: String
     * @author huajiejun
     * @version 创建时间：2017年5月25日 下午10:20:04
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {  
        return StringUtils.isBlank(encryptStr) ? null : decryptByBytes(base64Decode(encryptStr), decryptKey);
    }  
}
