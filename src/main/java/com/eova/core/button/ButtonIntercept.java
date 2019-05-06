/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.button;

import com.eova.aop.AopContext;
import com.eova.aop.MetaObjectIntercept;
import com.eova.model.RoleBtn;

public class ButtonIntercept extends MetaObjectIntercept {

	@Override
	public String deleteBefore(AopContext ac) throws Exception {
		int id = ac.record.getInt("id");

		// 删除菜单按钮关联权限
		RoleBtn.dao.deleteByBid(id);

		return null;
	}

}