package com.plq.exam.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author plq
 * @email 717208317@qq.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
//		put("error_code", 0);
	}

	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String error_msg) {
		return error(500, error_msg);
	}
	
	public static R error(int error_code, String error_msg) {
		R r = new R();
		r.put("error_code", error_code);
		r.put("error_msg", error_msg);
		return r;
	}

	public static R ok(String error_msg) {
		R r = new R();
		r.put("error_msg", error_msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
