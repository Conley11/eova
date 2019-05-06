/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 * 
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.type;

import java.util.HashMap;
import java.util.Map;

import com.eova.model.MetaField;

/**
 * SQLServer数据类型转换器
 * 如下内容未经过MSSQL测试,有同学需求的可以加入Eova&MSSQL研究小组QQ群:365303640
 * @author czhang
 * 
 */
@SuppressWarnings("rawtypes")
public class SqlServerConvertor extends Convertor {

	/**
	 * 参考：https://docs.microsoft.com/zh-cn/sql/connect/jdbc/using-basic-data-types
	 */
	@SuppressWarnings("serial")
	private final static Map<String, Class> map = new HashMap<String, Class>() {
		{
			put("char", String.class);
			put("nchar", String.class);
			put("varchar", String.class);
			put("nvarchar", String.class);
			put("text", String.class);
			put("ntext", String.class);
			put("xml", String.class);
			put("uniqueidentifier", String.class);

			put("bit", Boolean.class);

			put("smallint", Short.class);
			put("tinyint", Short.class);
			put("int", Integer.class);
			put("bigint", Long.class);

			put("real", Float.class);
			put("float", Double.class);

			put("money", java.math.BigDecimal.class);
			put("smallmoney", java.math.BigDecimal.class);
			put("numeric", java.math.BigDecimal.class);
			put("decimal", java.math.BigDecimal.class);

			put("date", java.util.Date.class);// 特殊处理
			put("datetime", java.util.Date.class);// 特殊处理
			put("datetime2", java.util.Date.class);// 特殊处理
			put("timestamp", java.sql.Timestamp.class);// 特殊处理
			put("smalldatetime", java.sql.Timestamp.class);
			put("time", java.sql.Time.class);

			put("binary", Byte[].class);
			put("udt", Byte[].class);
			put("varbinary", Byte[].class);

		}
	};

	@Override
	public Map<String, Class> mapping() {
		return map;
	}

	@Override
	public Object convert(MetaField field, Object o) {
		if (o == null) {
			return null;
		}

		return rule(o, getJavaType(field));
	}

}
