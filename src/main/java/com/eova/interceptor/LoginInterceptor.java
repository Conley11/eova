/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.interceptor;

import java.util.ArrayList;

import com.eova.common.utils.xx;
import com.eova.common.utils.util.AntPathMatcher;
import com.eova.config.EovaConst;
import com.eova.i18n.I18NBuilder;
import com.eova.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 常量加载拦截器
 * 
 * @author czhang
 * 
 */
public class LoginInterceptor implements Interceptor {

	/**
	 * 登录拦截排除URI<br>
	 * ?  匹配任何单字符<br> 
	 * *  匹配0或者任意数量的字符<br> 
	 * ** 匹配0或者更多的目录 <br>
	 */
	public static ArrayList<String> excludes = new ArrayList<String>();
	static {
		excludes.add("/captcha");
		excludes.add("/toLogin");
		excludes.add("/vcodeImg");
		excludes.add("/doLogin");
		excludes.add("/doInit");
		excludes.add("/toTest");
		excludes.add("/form");
		excludes.add("/doForm");
		excludes.add("/upgrade");
		excludes.add("/why");
	}

	@Override
	public void intercept(Invocation inv) {
		
		sessionInterceptor(inv);
		
		String uri = inv.getActionKey();

		AntPathMatcher pm = new AntPathMatcher();
		for (String pattern : excludes) {
			if (pm.match(pattern, uri)) {
				inv.invoke();
				return;
			}
		}

		// 获取登录用户的角色
		User user = inv.getController().getSessionAttr(EovaConst.USER);
		if (user == null) {
			inv.getController().redirect("/toLogin");
			return;
		}

		inv.invoke();
	}
	
	/**
	 * 当前会话拦截器管理
	 * PS:服务器网络模型不同导致新建线程策略不同,每次请求都会被一个线程处理,但是可能并不是一个新线程,比如线程池.
	 * @param inv
	 */
	public void sessionInterceptor(Invocation inv){
		String local = (String) inv.getController().getSessionAttr(EovaConst.LOCAL);
		if (!xx.isEmpty(local)) {
			I18NBuilder.setLocal(local);
		}
	}
}