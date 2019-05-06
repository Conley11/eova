/**
 * Copyright (c) 2015-2018, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at admin@eova.cn
 */
package com.eova.common.render;

import java.util.HashMap;

import com.jfinal.kit.PathKit;
import com.jfinal.render.HtmlRender;

public class ResourceRender extends HtmlRender {

	public ResourceRender(Object object, String view, HashMap<String, Object> attr) {
		super(RenderUtil.renderResource(buildResource(object, view), attr));
	}

	private static String buildResource(Object object, String filePath) {
		// 获取当前方法的上上级 也就是 调用
		// StackTraceElement[] ss = Thread.currentThread().getStackTrace();
		// StackTraceElement a = (StackTraceElement)ss[4];
		// String txt = Utils.readFromResource(filePath);
		String pack = PathKit.getPackagePath(object);
		return String.format("%s/resources/%s", pack, filePath);
	}

}
