package com.weroom.util;

/*
 --------------------------------------------**********--------------------------------------------
 该算法于1977年由美国麻省理工学院MIT(Massachusetts Institute of Technology)的Ronal Rivest，Adi Shamir和Len Adleman三位年轻教授提出，并以三人的姓氏Rivest，Shamir和Adlernan命名为RSA算法，是一个支持变长密钥的公共密钥算法，需要加密的文件快的长度也是可变的!
 所谓RSA加密算法，是世界上第一个非对称加密算法，也是数论的第一个实际应用。它的算法如下：
 1.找两个非常大的质数p和q（通常p和q都有155十进制位或都有512十进制位）并计算n=pq，k=(p-1)(q-1)。
 2.将明文编码成整数M，保证M不小于0但是小于n。
 3.任取一个整数e，保证e和k互质，而且e不小于0但是小于k。加密钥匙（称作公钥）是(e, n)。
 4.找到一个整数d，使得ed除以k的余数是1（只要e和n满足上面条件，d肯定存在）。解密钥匙（称作密钥）是(d, n)。
 加密过程： 加密后的编码C等于M的e次方除以n所得的余数。
 解密过程： 解密后的编码N等于C的d次方除以n所得的余数。
 只要e、d和n满足上面给定的条件。M等于N。
 --------------------------------------------**********--------------------------------------------
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;

/**
 * @author Administrator
 */
public class RSA {
	private static final Logger log = LoggerFactory.getLogger(RSA.class);
	public static final String clientPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALM9gyzoliA8R2da" +
			"9tqfh5c7V/Zcn5etZDNmeqv/1YROoe5O4Vo8N4lBSGP+IKSXsSfCm13v3ZDrxqUp" +
			"b6YCBnz17W3DR9TxUNQraB4y286fRPsl6N/OMB8EhhCTKuvHyjqgq8sIY5ymg9PV" +
			"sIetJglVp870hRRDYvJdRaUyf5DpAgMBAAECgYBz+QT69bLNQLNiqHS4mCf/LiKI" +
			"AP4yAbqnl1MgHvRkGwyjSN5+qB+260qPiEpOtOenNj+Y1C6kKinpi5n167GN60Y6" +
			"gM/vVHSUA/lv2v/fR4vDRj9CMYu2vNuKrWV3F3y577/qkuCIWaD+iXU9WsQTsuL3" +
			"UkP5iz3sVgD9K7kwAQJBAOPjKmmKEghwGaCXx05E0BF7UHT4b7BZhCPdCj115PAL" +
			"0xNqqKYyRhYGfndopcB16Jl7fJJLxjj+02V2YsfkW4ECQQDJWgofvt8Qjf4VLUnl" +
			"98rnQ6xF2UbXdSOm9JCILU7rtz92TVC7bWy4R0mqX+S5zFGR5Znnk7f2HAP+kDAn" +
			"3olpAkAkeQIbvB5gvVhMrJ4Yd8j5U0wwPGbbyafteX4fEhRfGvVgYSy21MsE0WtC" +
			"PzxwDi75CZuJaplCG/7HeWgb5/+BAkBtXnkNUVI83cPboOP5BCW0hK+4qRKvybL5" +
			"5vHy5hluM/VGvyxRAlkBp8c9wiStP2w3QW3dugE8r28EATIHFfuxAkAxV4hHGD6B" +
			"bHRiCtSMNOQ3XGOZGdlmR4PfPFabv+jfAyoECHe4QHdkBBqdNKTyVVQUpNqPsJ0E" +
			"sfBTplZlDxTu";

	public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzPYMs6JYgPEdnWvban4eXO1f2" +
			"XJ+XrWQzZnqr/9WETqHuTuFaPDeJQUhj/iCkl7Enwptd792Q68alKW+mAgZ89e1t" +
			"w0fU8VDUK2geMtvOn0T7JejfzjAfBIYQkyrrx8o6oKvLCGOcpoPT1bCHrSYJVafO" +
			"9IUUQ2LyXUWlMn+Q6QIDAQAB";

	public static final String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANG5Xmt9GeApu/na" +
			"xAcDol7LSArS0vIusyyVjA5U7vy5u1M2zgQa/EWipX/eP5agtRmWjOh2kER0mTAc" +
			"luc2GLGLXKKppekQMf4dcYX0d6zkOlz60B/AVBjEfJVwvI04RjPxLT/t/yQ7foAp" +
			"dWba+FXZ+U3N+Bk7auC0ZOmtzYpTAgMBAAECgYARxFJraL24b9Cs6guRTI2E2lPQ" +
			"Fuwn+CzVqhWjYS6d2l40PoBVeLPGcnDy1DEu4Y52DHsDdofiRL51hPaDv+F3gcO/" +
			"JhLaH1s9p6I6SV3fuCE8mVK2gRoyQsGJv/PuZePyOYlarTbkbpaOZroTyCIdbNe9" +
			"W1Xj1EbzXVWvVyBeAQJBAOqOxnuk5+tUCL4OmlYAKWnrb0Zev8y5m23xohoVMnvd" +
			"qY2+ImCDpyWtwzGrupxyHftRuq8MFlKzVvBaH6p34NECQQDk5W0QZs4jJOumN03x" +
			"dOGiGlSA1Pq0H94fxiG/hX5u1hdqRxUib5vRb35iXvO0SvEX9gs7i2eDJdk+1FlI" +
			"smHjAkBcB90U0mU0zmoHuE8SA0o8huXIJJD40LKNdst1lG+UbiqCtOFkIQPKIt19" +
			"dbXogFYHL9AhsopntHoeIB5gklyhAkEAvLaqhKs9qJv37MIL8NZ8cDllEhKF35um" +
			"dmvTxyM4agj4pRaVxh5eSP2zJUdDhZGD1E12VVFJtkauJa7NLbJkIQJAOtwDgkrf" +
			"WrhC8/hPusa51GBVfB/SkhQzxaCb+oFf0qSe25J4fJLdcX20vLLCvF65Es8BH1V9" +
			"NSf8MPqLW0UjrQ==";
	public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRuV5rfRngKbv52sQHA6Jey0gK" +
			"0tLyLrMslYwOVO78ubtTNs4EGvxFoqV/3j+WoLUZlozodpBEdJkwHJbnNhixi1yi" +
			"qaXpEDH+HXGF9Hes5Dpc+tAfwFQYxHyVcLyNOEYz8S0/7f8kO36AKXVm2vhV2flN" +
			"zfgZO2rgtGTprc2KUwIDAQAB";
	/** 指定key的大小 */
	private static int KEYSIZE = 2048;
	/**
	 * 生成密钥对
	 */
	public static Map<String, String> generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);

		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static String encrypt(String source, String publicKey)
			throws Exception {
		Key key = getPublicKey(publicKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		return new String(Base64.encodeBase64(b1),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph, String privateKey)
			throws Exception {
		Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	public static Req client(TreeMap<String, Object> params) throws Exception {
		// 生成RSA签名
		String sign = EncryUtil.handleRSA(params, clientPrivateKey);
		params.put("sign", sign);

		String info = JSON.toJSONString(params);
		//随机生成AES密钥
		String aesKey = SecureRandomUtil.getRandom(16);
		//AES加密数据
		String data = AES.encryptToBase64(ConvertUtils.stringToHexString(info), aesKey);

		// 使用RSA算法将商户自己随机生成的AESkey加密
		String encryptkey = RSA.encrypt(aesKey, serverPublicKey);

		Req.data = data;
		Req.encryptkey = encryptkey;
		System.out.println("加密后的请求数据:\n" + new Req());
		return new Req();
	}

	public static String server() throws Exception {

		// 验签
		boolean passSign = EncryUtil.checkDecryptAndSign(Req.data,
				Req.encryptkey, clientPublicKey, serverPrivateKey);

		if (passSign) {
			// 验签通过
			String aeskey = RSA.decrypt(Req.encryptkey,
					serverPrivateKey);
			String data = ConvertUtils.hexStringToString(AES.decryptFromBase64(Req.data,
					aeskey));

			JSONObject jsonObj = JSONObject.parseObject(data);
			String userid = jsonObj.getString("userid");
			String phone = jsonObj.getString("phone");
			System.out.println("解密后的明文:userid:" + userid + " phone:" + phone);
			return jsonObj.toJSONString();
		} else {
			System.out.println("验签失败");
			return "";
		}
	}


	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String sign(String content, String privateKey) {
		String charset = ConfigureEncryptAndDecrypt.CHAR_ENCODING;
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA256WithRSA");

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {

		}

		return null;
	}
	
	public static boolean checkSign(String content, String sign, String publicKey)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode2(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance("SHA256WithRSA");
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes("utf-8") );
		
			boolean bverify = signature.verify( Base64.decode2(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}
		
		return false;
	}	

}