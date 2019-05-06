/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.aop.eova;

import com.eova.config.EovaConst;
import com.eova.engine.EovaExp;
import com.eova.model.Menu;
import com.eova.model.MetaObject;
import com.eova.model.User;
import com.jfinal.core.Controller;

/**
 * Eova全局业务拦截器上下文
 *
 * @author czhang
 * @date 2018-8-29
 */
public class EovaContext {

	/**
	 * 当前控制器
	 */
	public Controller ctrl;

	/**
	 * 当前用户对象
	 */
	public User user;

	/**
	 * 当前菜单
	 */
	public Menu menu;

	/**
	 * 当前元对象
	 * 元字段=object.fields
	 * 
	 */
	public MetaObject object;

	/**
	 * 当前操作表达式
	 */
	public EovaExp exp;

	public EovaContext(Controller ctrl) {
		this.ctrl = ctrl;
		this.user = (User) ctrl.getSessionAttr(EovaConst.USER);
	}

	public int UID() {
		return this.user.get("id");
	}
}