/**
 * Copyright (c) 2011-2017, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eova.ext.jfinal;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eova.core.type.OracleConvertor;
import com.jfinal.plugin.activerecord.CPI;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.ModelBuilder;

/**
 * Oracle专用Model构建器
 * @author czhang
 *
 */
public class OracleModelBuilder extends ModelBuilder {
	
	public static final OracleModelBuilder me = new OracleModelBuilder();
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public <T> List<T> build(ResultSet rs, Class<? extends Model> modelClass) throws SQLException, InstantiationException, IllegalAccessException {
		List<T> result = new ArrayList<T>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		String[] labelNames = new String[columnCount + 1];
		int[] types = new int[columnCount + 1];
		buildLabelNamesAndTypes(rsmd, labelNames, types);
		while (rs.next()) {
			Model<?> ar = modelClass.newInstance();
			Map<String, Object> attrs = CPI.getAttrs(ar);
			for (int i=1; i<=columnCount; i++) {
				Object value;

				if (types[i] == Types.NUMERIC) {
					int p = rsmd.getPrecision(i);
					int s = rsmd.getScale(i);
					if (s == 0) {
						if (p <= 9) {
							value = rs.getInt(i);
						} else if (p <= 18) {
							value = rs.getLong(i);
						} else {
							value = rs.getBigDecimal(i);
						}
					} else {
						if (p + s <= 8) {
							value = rs.getFloat(i);
						} else if (p + s <= 16) {
							value = rs.getDouble(i);
						} else {
							value = rs.getBigDecimal(i);
						}
					}
				} else {
					if (types[i] == Types.TIMESTAMP) {
						value = rs.getTimestamp(i);
					} else if (types[i] == Types.DATE) {
						value = rs.getDate(i);
					} else if (types[i] == Types.CLOB) {
						value = handleClob(rs.getClob(i));
					} else if (types[i] == Types.NCLOB) {
						value = handleClob(rs.getNClob(i));
					} else if (types[i] == Types.BLOB) {
						value = handleBlob(rs.getBlob(i));
					} else {
						// Eova Oracle 特殊处理
						value = OracleConvertor.convertValue(rs.getObject(i), types[i]);
					}
				}
				

				attrs.put(labelNames[i], value);
			}
			result.add((T)ar);
		}
		return result;
	}
}



