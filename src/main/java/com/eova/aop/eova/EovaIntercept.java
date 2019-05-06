/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.aop.eova;

/**
 * <pre>
 * 全局Eova业务拦截器
 * </pre>
 * @author czhang
 *
 */
public class EovaIntercept {

	/**
	 * <pre>
	 * 过滤查询数据
	 * 
	 * ac.ctrl 当前控制器
	 * ac.user 当前用户
	 * ac.menu 当前菜单
	 * ac.object 当前元对象
	 * ec.object.getFields() 当前元字段
	 * </pre>
	 * @param ac
	 */
	public String filterQuery(EovaContext ec) throws Exception {
		return "";
	}

	/**
	 * <pre>
	 * 过滤表达式数据
	 * 
	 * ac.ctrl 当前控制器
	 * ac.user 当前用户
	 * ac.exp 当前表达式
	 * </pre>
	 * @param ac
	 */
	public String filterExp(EovaContext ec) throws Exception {
		return "";
	}

}