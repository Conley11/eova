/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.dict;

import com.eova.common.Easy;
import com.eova.common.base.BaseController;
import com.eova.common.utils.xx;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 字典管理
 * @author czhang
 *
 */
public class DictController extends BaseController {

	// 复制字典
	public void copy() {
		String id = getSelectValue("id");

		String dictTable = xx.getConfig("main_dict_table");

		Record r = Db.use(xx.DS_MAIN).findById(dictTable, id);
		r.remove("id");
		Db.use(xx.DS_MAIN).save(dictTable, r);

		renderJson(new Easy());
	}
}