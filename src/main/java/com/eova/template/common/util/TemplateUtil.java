/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.template.common.util;

import com.eova.common.utils.xx;
import com.eova.common.utils.util.ExceptionUtil;
import com.eova.config.EovaConfig;
import com.eova.model.Button;
import com.eova.model.MetaField;

public class TemplateUtil {

	/**
	 * 值的类型转换
	 * 
	 * @param item 元字段
	 * @param value
	 * @return
	 */
	public static Object buildValue(MetaField item, Object value) {
		// 控件类型
		String type = item.getStr("type");
		// 数据类型
		// String dataType = item.getDataTypeName();
		// 布尔框需要特转换值
		if (type.equals(MetaField.TYPE_BOOL)) {
			if (xx.isTrue(value)) {
				return 1;
			} else {
				return 0;
			}
		}
		return value;
	}

	/**
	 * 构建异常信息为HTML
	 * 
	 * @param e
	 * @return
	 */
	public static String buildException(Exception e) {
		e.printStackTrace();

		String type = e.getClass().getName();
		type = type.equals("java.lang.Exception") ? e.getMessage() : type;
		return "<br/><p style=\"color:red\" title=\"" + ExceptionUtil.getStackTrace(e) + "\">" + type + " [查看异常]</p>";
	}

	/**
	 * 初始化业务拦截器
	 * 
	 * @param bizIntercept
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T initIntercept(String bizIntercept) throws Exception {
		if (!xx.isEmpty(bizIntercept)) {
			try {
				// 实例化自定义拦截器
				return (T) Class.forName(bizIntercept).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("实例化拦截器异常，请检查类是否存在:" + bizIntercept);
			}
		}
		return null;
	}
	
	/**
	 * 初始化元对象拦截器
	 * 
	 * @param bizIntercept
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T initMetaObjectIntercept(String bizIntercept) throws Exception {
		Object o = initIntercept(bizIntercept);
		if (o == null) {
			// 命中默认拦截器(如果有)
			return (T) EovaConfig.getDefaultMetaObjectIntercept();
		}
		return (T)o;
	}

	/**
	 * 默认查询按钮
	 * @return
	 */
	public static Button getQueryButton() {
		Button btn = new Button();
		btn.set("name", "查询");
		btn.set("ui", "query");
		return btn;
	}
}