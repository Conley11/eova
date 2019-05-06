package com.eova.common.base;

import java.util.Map;

import com.eova.common.utils.web.HtmlUtil;

public class BaseSharedMethod {

	private Map<String, String> conf;

	public BaseSharedMethod(Map<String, String> conf) {
		this.conf = conf;
	}

	public String htt(String domain) {
		return "//" + conf.get(domain);
	}

	public String http(String domain) {
		return "http://" + conf.get(domain);
	}

	public String https(String domain) {
		return "https://" + conf.get(domain);
	}

	public String dir(String dirName) {
		return conf.get("dir.static") + conf.get("dir." + dirName);
	}

	//	 非法内容过滤,适用于纯文本输出
	public String xss(String s) {
		return HtmlUtil.XSSEncode(s);
	}

	// html内容转码
	public String html(String s) {
		return HtmlUtil.HTMLEncode(s);
	}
}