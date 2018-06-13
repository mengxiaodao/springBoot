package com.mmc.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * json工具类,提供常用的工具方法
 * 
 */
public class JsonUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(JsonUtils.class);
	
	/**
	 * 默认的日期格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 单例模式
	 */
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	/**
	 * 转换为json.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if(null == object){
			return "";
		}
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 转换为jsonp.
	 * 
	 * @param functionName
	 * @param object
	 * @return
	 */
	public static String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(Encodes.urlEncode(functionName),
				object));
	}

	/**
	 * json转换为对象.
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:", e);
			return null;
		}
	}

	/**
	 * Description ： 将map转换为对象<br>
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @since
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T map2obj(Map map, Class<T> clazz) {
		return JsonUtils.fromJson(JsonUtils.toJson(map), clazz);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
	 * 
	 * @see #constructParametricType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.error("parse json string error:", e);
			return null;
		}
	}

	/**
	 * 
	 * @param jsonString
	 * @param parametrized 泛型类	
	 * @param parameterClasses 泛型参
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, Class<?> parametrized,
			Class<?>... parameterClasses) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		return (T) fromJson(jsonString, constructParametricType(parametrized, parameterClasses));
	}

	/**
	 * 構造泛型的Type如List<MyBean>,
	 * 则调用constructParametricType(ArrayList.class,MyBean.class)
	 * Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class)
	 */
	public static JavaType constructParametricType(Class<?> parametrized,
                                                   Class<?>... parameterClasses) {
		return OBJECT_MAPPER.getTypeFactory().constructParametricType(
				parametrized, parameterClasses);
	}

	/**
	 * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T update(T object, String jsonString) {
		try {
			return (T) OBJECT_MAPPER.readerForUpdating(object).readValue(
					jsonString);
		} catch (JsonProcessingException e) {
			logger.error("update json string:" + " to object:"
					+ object + " error.", e);
		} catch (IOException e) {
			logger.error("update json string:" + " to object:"
					+ object + " error.", e);
		}
		return null;
	}
}
