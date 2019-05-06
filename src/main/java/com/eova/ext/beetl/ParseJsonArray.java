/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.ext.beetl;

import org.beetl.core.Context;
import org.beetl.core.Function;

import com.alibaba.fastjson.JSONArray;

/**
 * 字符串转JSONArray
 * 
 * @author czhang
 * @date 2017-07-16
 */
public class ParseJsonArray implements Function {
	@Override
	public Object call(Object[] paras, Context ctx) {
		if (paras.length != 1) {
			throw new RuntimeException("参数错误，请传入一个JSON字符串");
		}
		Object para = paras[0];
		if (para == null) {
			return null;
		}
		return JSONArray.parse(para.toString());
	}
}