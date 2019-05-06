/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.oss.model;

import com.eova.common.base.BaseModel;
import com.jfinal.plugin.activerecord.Db;

/**
 * 订单
 * @author czhang
 *
 */
public class Orders extends BaseModel<Orders> {

	private static final long serialVersionUID = 1064291771401662738L;

	public static final Orders dao = new Orders();
	
	public int updateState(int id, int state) {
		return Db.update("update orders set state = ? where id = ?", state, id);
	}
}