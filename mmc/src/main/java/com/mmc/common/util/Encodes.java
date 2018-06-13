/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package com.mmc.common.util;

import com.mmc.model.dto.SysUserDto;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
/**
 * 封装各种格式的编码解码工具类.
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * @author calvin
 * @version 2013-01-15
 */
public class Encodes {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	public static final String SHA1 = "SHA-1";
	private static SecureRandom random = new SecureRandom();
	/**
	 * 生成盐的长度
	 */
	public static final int SALT_SIZE = 8;
	/**
	 * 生成Hash值的迭代次数
	 */
	public static final int HASH_INTERATIONS = 1024;
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static void entryptPassword(SysUserDto userDto) {
		byte[] salt = generateSalt(SALT_SIZE);
		userDto.setSalt(encodeHex(salt));
		byte[] hashPassword = sha1(userDto.getPassword().getBytes(), salt, HASH_INTERATIONS);
		userDto.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 修改密码用
	 * @param paramStr 输入需要加密的字符串
	 * @return
	 */
	public static String entryptPassword(String paramStr,String salt) {
		if(StringUtils.isNotEmpty(paramStr)){
			byte[] saltStr = Encodes.decodeHex(salt);
			byte[] hashPassword = sha1(paramStr.getBytes(), saltStr, HASH_INTERATIONS);
			String password = Encodes.encodeHex(hashPassword);
			return password;
		}else{
			return null;
		}
	}

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw Exceptions.unchecked(e);
		}
	}


	/**
	 * 生成随机的Byte[]作为salt.
	 *
	 * @param numBytes byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}


	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}
	
	/**
	 * Base64编码.
	 */
	public static String encodeBase64(String input) {
		try {
			return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

//	/**
//	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
//	 */
//	public static String encodeUrlSafeBase64(byte[] input) {
//		return Base64.encodeBase64URLSafe(input);
//	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input.getBytes());
	}
	
	/**
	 * Base64解码.
	 */
	public static String decodeBase64String(String input) {
		try {
			return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

//	/**
//	 * Html 转码.
//	 */
//	public static String escapeHtml(String html) {
//		return StringEscapeUtils.escapeHtml4(html);
//	}
//
//	/**
//	 * Html 解码.
//	 */
//	public static String unescapeHtml(String htmlEscaped) {
//		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
//	}
//
//	/**
//	 * Xml 转码.
//	 */
//	public static String escapeXml(String xml) {
//		return StringEscapeUtils.escapeXml10(xml);
//	}
//
//	/**
//	 * Xml 解码.
//	 */
//	public static String unescapeXml(String xmlEscaped) {
//		return StringEscapeUtils.unescapeXml(xmlEscaped);
//	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static void main(String[] args) {
		SysUserDto dto = new SysUserDto();
		dto.setLoginName("admin");
		dto.setPassword("admin");
		entryptPassword(dto);
		System.out.println(dto.getPassword());
		System.out.println(dto.getSalt());
	}
}
