/**
 * Copyright (c) 2015-2018, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at admin@eova.cn
 */
package com.eova.common.render;

import java.io.InputStream;
import java.util.Map;

import org.beetl.core.Template;

import com.eova.common.utils.io.StreamUtil;
import com.eova.common.utils.io.TxtUtil;
import com.eova.engine.DynamicParse;

public class RenderUtil {


	/**
	 * 渲染指定文件
	 * @param path 文件路径
	 * @param attr 动态参数
	 * @return
	 */
	public static String renderFile(String path, Map<String, Object> attr) {
		return parseTemplate(TxtUtil.getTxt(path), attr);
	}

	/**
	 * 渲染指定资源文件(比如Jar内资源文件)
	 * @param resource 资源文件路径
	 * @param attr 动态参数
	 * @return
	 */
	public static String renderResource(String resource, Map<String, Object> attr) {
		String text = "";
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			if (in == null) {
				in = RenderUtil.class.getResourceAsStream(resource);
			}
			if (in == null) {
				return null;
			}
			text = TxtUtil.read(in);
		} finally {
			StreamUtil.close(in);
		}
		return parseTemplate(text, attr);
	}

	private static String parseTemplate(String temp, Map<String, Object> attr) {
		Template t = DynamicParse.gt.getTemplate(temp);
		for (String key : attr.keySet()) {
			Object o = attr.get(key);
			t.binding(key, o);
		}
		return t.render();
	}
}
