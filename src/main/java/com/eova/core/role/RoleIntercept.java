/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.role;

import com.eova.aop.AopContext;
import com.eova.aop.MetaObjectIntercept;

public class RoleIntercept extends MetaObjectIntercept {

	@Override
	public String addBefore(AopContext ac) throws Exception {
		Integer lv = ac.record.getInt("lv");
		Integer roleLv = ac.user.getRole().getInt("lv");
		if (lv <= roleLv) {
			return error("权限级别必须大于：" + roleLv);
		}
		return null;
	}

	@Override
	public String updateBefore(AopContext ac) throws Exception {
		return addBefore(ac);
	}

	

}