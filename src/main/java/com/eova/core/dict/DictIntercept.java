/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.dict;

import com.eova.aop.AopContext;
import com.eova.aop.MetaObjectIntercept;
import com.eova.common.utils.xx;
import com.eova.model.MetaObject;

public class DictIntercept extends MetaObjectIntercept {

	@Override
	public void queryBefore(AopContext ac) throws Exception {
		String objectCode = ac.ctrl.getPara("query_v_object_code");
		if (!xx.isEmpty(objectCode)) {
			MetaObject o = MetaObject.dao.getByCode(objectCode);
			ac.condition = " and object = ?";
			ac.params.add(o.getTable());
		}

		super.queryBefore(ac);
	}

	
}