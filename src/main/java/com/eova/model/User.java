/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.model;

import com.eova.common.base.BaseModel;
import com.eova.config.EovaConst;

public class User extends BaseModel<User> {

	private static final long serialVersionUID = 1064291771401662738L;

	public static final User dao = new User();

	public Role role;
	
	public int getRid(){
		return this.getInt("rid");
	}
	
	/**
	 * 是否超级管理员
	 * @return
	 */
	public boolean isAdmin(){
		return getIsAdmin();
	}
	
	// 为兼容模版取值
	public boolean getIsAdmin(){
		if (this.getRid() == EovaConst.ADMIN_RID) {
			return true;
		}
		return false;
	}

	public void init() {
		this.role = Role.dao.findById(this.getInt("rid"));
	}

	public User getByLoginId(String loginId) {
		return this.findFirst("select * from eova_user where login_id = ?", loginId);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}