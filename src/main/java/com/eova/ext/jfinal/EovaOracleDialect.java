/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.ext.jfinal;

import com.jfinal.plugin.activerecord.dialect.OracleDialect;

/**
 * 拓展Oracle方言:个性化识别Number类型和Boolean类型
 * 
 * @author czhang
 *
 */
public class EovaOracleDialect extends OracleDialect {

	public EovaOracleDialect() {
		this.modelBuilder = OracleModelBuilder.me;
		this.recordBuilder = OracleRecordBuilder.me;
	}

}
