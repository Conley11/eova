/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.i18n;

import java.util.HashMap;

import com.eova.common.utils.xx;

public class I18N extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	public String get(String key) {
		String s = super.get(key);
		if (xx.isEmpty(s)) {
			return key;
		}
		return s;
	}

}