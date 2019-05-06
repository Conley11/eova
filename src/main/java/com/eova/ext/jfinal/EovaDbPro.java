/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.ext.jfinal;

import com.eova.common.utils.xx;
import com.eova.config.EovaConst;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Record;

/**
 * 拓展默认Db操作<br>
 * 1.常用聚合API自动类型转换,使用更顺滑<br>
 * 2.Eova Oracle序列的自动识别
 * 
 * @author czhang
 *
 */
public class EovaDbPro extends DbPro {

	public EovaDbPro(String configName) {
		super(configName);
	}

	@Override
	public boolean save(String tableName, String primaryKey, Record record) {
		// Oracle 并且是单主键 设置默认序列
		if (xx.isOracle() && !primaryKey.contains(",") && record.get(primaryKey) == null) {
			record.set(primaryKey, EovaConst.SEQ_ + tableName + ".nextval");
		}
		return super.save(tableName, primaryKey, record);
	}

}
