/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.template.office;

import java.util.HashMap;

import com.jfinal.core.Controller;

public class OfficeIntercept {

	protected final static char Y = '☑';
	protected final static char N = '□';

	/**
	 * 初始化获取模版数据
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void init(Controller ctrl, HashMap<String, Object> data) throws Exception {
	}

}