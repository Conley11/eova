/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.model;

import com.eova.common.base.BaseModel;
import com.eova.common.utils.web.RequestUtil;
import com.eova.config.EovaConst;
import com.jfinal.core.Controller;

/**
 * 系统操作日志
 *
 * @author czhang
 * @date 2018-9-10
 */
public class EovaLog extends BaseModel<EovaLog> {

	private static final long serialVersionUID = -1592533967096109392L;

	public static final EovaLog dao = new EovaLog();

	/** 新增 **/
	public static final int ADD = 1;
	/** 修改 **/
	public static final int UPDATE = 2;
	/** 删除 **/
	public static final int DELETE = 3;
	/** 导入 **/
	public static final int IMPORT = 4;
	
	/**
	 * 操作日志
	 * @param con
	 * @param info 日志详情
	 */
	public void info(Controller con, int type, String info){
		EovaLog el = new EovaLog();
		// TYPE
		el.set("type", type);
		// UID
		User user = con.getSessionAttr(EovaConst.USER);
		el.set("user_id", user.get("id"));
		// IP
		String ip = RequestUtil.getIp(con.getRequest());
		el.set("ip", ip);
		el.set("info", info);
		el.save();
	}
}