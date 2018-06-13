package com.mmc.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmc.common.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Result implements Serializable{
	/**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3686972211525799918L;
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    /** state 常量 **/
    /** 登陆超时状态 **/
	public static final Integer SESSION_TIMEOUT = -1;
    /** 失败状态 **/
	public static final Integer INPUT = 0;
    /** 成功状态 **/
	public static final Integer SUCCESS = 1;
    /** 失败状态使用全局提示 **/
	public static final Integer INPUT_AUTO = 2;
    /** 成功状态使用全局提示 **/
	public static final Integer SUCCESS_AUTO = 3;
	
    /** [异常状态码]失败状态使用全局提示 用于结算页面tradpage特殊处理,需要弹出提示的时候返回购物车 **/
    public static final Integer TRAD_INPUT_AUTO = -999;
    /** [异常状态码]异步请求对于登陆的拦截 **/
    public static final Integer NOT_LOGIN = -998;
    /** [异常状态码]main.dec中不弹出，给页面自己处理 **/
    public static final Integer NOT_ALERT = -997;
    /**
     * [异常状态码]登陆已过期，需刷新页面
     */
    public static final Integer LOGIN_OUT_DATE = -996;
    /**
     * SOA运行时业务异常
     */
    public static final Integer SOA_BUSINESS_EXCPTION = -995;
    /**
     * SOA运行时非业务异常
     */
    public static final Integer SOA_RUNTIME_EXCPTION = -1001;
    
	private Integer state = Result.SUCCESS;
	private boolean success = true;
	private String message = "";
	
	private final Map<String, Object> data = new HashMap<String, Object>();

	public Result() {
	}

    /**
     * 
     * @param success 是否成功
     */
	public Result(boolean success) {
		this.success = success;
	}

	/**
     * 
     * @param success 是否成功
     * @param state 状态码
     */
	public Result(boolean success, Integer state) {
		this.success = success;
		this.state = state;
	}

	public Result(boolean success, String msg) {
		this.success = success;
		this.message = msg;
	}

	public Result(Integer state, String msg) {
		this.state = state;
		this.message = msg;
	}

	public Result(boolean success, Integer state, String msg) {
		this.success = success;
		this.state = state;
		this.message = msg;
	}

	/**
     * 将属性绑定至request中
     * 
     * @param request
     */
	public void rendering(HttpServletRequest request) {
//		Set<String> keySet = data.keySet();
//		for (String key : keySet) {
//			request.setAttribute(key, data.get(key));
//		}
		for(Map.Entry<String, Object> entry : data.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
	}

	/**
     * 将属性绑定至request中
     */
	public void rendering() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
//		Set<String> keySet = data.keySet();
//		for (String key : keySet) {
//			request.setAttribute(key, data.get(key));
//		}
		for(Map.Entry<String, Object> entry : data.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
	}

	/**
     * 将属性绑定至Model中
     * 
     * @param model
     */
	public void rendering(Model model) {
		model.addAllAttributes(data);
	}

	/**
     * 将属性绑定至ModelAndView中
     * 
     * @param model
     */
	public void rendering(ModelAndView model) {
		model.addAllObjects(data);
	}

	// ------------------------------------------------
	private static final String JSON_CONTENT_TYPE = "text/html; charset=UTF-8";

	/**
     * 
     * 输出result中的键值只response中，值需为json格式
     * 
     * @param response
     */
	public void renderingByJsonData(HttpServletResponse response) {
		if (response.getContentType() == null){
			response.setContentType(JSON_CONTENT_TYPE);
		}
		PrintWriter writer = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("state", this.getState());
			map.put("success", this.isSuccess());
			map.put("data", this.data);
			map.put("message", this.message);
			String str = JsonUtils.toJson(map);
			writer = response.getWriter();
			writer.print(str);
			writer.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
     * 输出result中的键值只response中，值需为jsonp格式
     *
     * @param response
     * @param functionName
     */
	public void renderingByJsonPData(HttpServletResponse response,
			String functionName) {
		if (response.getContentType() == null)
			response.setContentType(JSON_CONTENT_TYPE);

		PrintWriter writer = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("state", this.getState());
			map.put("success", this.isSuccess());
			map.put("data", this.data);
			map.put("message", this.message);
			String str = JsonUtils.toJsonP(functionName, map);
			writer = response.getWriter();
			writer.print(str);
			writer.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
     * 增加属性
     * 
     * @param key
     * @param obj
     */
	public void addAttribute(String key, Object obj) {
		data.put(key, obj);
	}

	/**
     * 追加所有属性
     * 
     * @param result
     */
	public void addAttrbutes(Result result) {
		data.putAll(result.getData());
	}
	
	public void addAttributes(Map<String,Object> map) {
		data.putAll(map);
	}

	/**
     * 获得属性
     * 
     * @param key
     */
	@JsonIgnore
	public Object getAttribute(String key) {
		return data.get(key);
	}

	/**
     * 获得属性
     * 
     * @param key
     * @param type
     * @return
     */
    @JsonIgnore
	public <T> T getAttribute(String key, Class<T> type) {
		if (data.get(key) == null) {
			return null;
		}
		return (T) data.get(key);
	}

    /**
     * 检查key是否存在
     * 
     * @param key
     * @return
     */
	public boolean containsKey(String key) {
		return data.containsKey(key);
	}

	            /**
     * 检查value是否存在
     * 
     * @param value
     * @return
     */
	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}

    /**
     * 判断result是否存在键值
     * 
     * @return
     */
	@JsonIgnore
	public boolean isEmpty() {
		return data.isEmpty();
	}

    /**
     * 获取所有属性
     * 
     * @return
     */
	@JsonIgnore
	public Map<String, ?> getAttribute() {
		return data;
	}

	@Override
	public String toString() {
		return toJsonString();
	}

	public String toJsonString() {
		@SuppressWarnings("unused")
		Map map = new HashMap();
		return JsonUtils.toJson(this);
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStateAndSuccess(Integer state,boolean success,String message) {
	    setState(state);
	    setSuccess(success);
	    setMessage(message);
	}
}
