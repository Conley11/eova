/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.ext.beetl.format;

import org.beetl.core.Format;

public class TimeFormat implements Format {

	@Override
	public Object format(Object data, String pattern) {
		if (null == data) {
			return null;
		} else {
			String str = data.toString();
//			new DateTime();
//			// XSS简单过滤
//			content = HtmlUtil.XSSEncode(content);
			return str;
		}
	}
}