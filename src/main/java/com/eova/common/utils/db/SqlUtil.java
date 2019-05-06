package com.eova.common.utils.db;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.eova.common.utils.xx;
import com.eova.config.EovaConfig;

public class SqlUtil {

	/**
	 * 向SQL中插入条件
	 * @param sql
	 * @param condition
	 * @param dbType
	 * @return
	 */
	public static String addCondition(String sql, String condition) {
		if (xx.isEmpty(condition)) {
			return sql;
		}
		condition = condition.trim().toLowerCase();

		SQLBinaryOperator op = SQLBinaryOperator.BooleanAnd;

		if (condition.startsWith("and")) {
			op = SQLBinaryOperator.BooleanAnd;
			condition = condition.replace("and ", "");
		} else if (condition.startsWith("or")) {
			op = SQLBinaryOperator.BooleanOr;
			condition = condition.replace("or ", "");
		} else if (condition.startsWith("xor")) {
			op = SQLBinaryOperator.BooleanXor;
			condition = condition.replace("xor ", "");
		}

		return notNewLine(SQLUtils.addCondition(sql, condition, op, false, EovaConfig.EOVA_DBTYPE));
	}

	/**
	 * 追加Where条件
	 * @param where where子句
	 * @param condition where条件
	 * @return
	 */
	public static String appendWhereCondition(String where, String condition) {
		if (xx.isEmpty(condition))
			return where;

		// 格式化
		condition = condition.trim();
		where = where == null ? "" : where.trim();

		// where子句自动补头
		if (where.toLowerCase().indexOf("where") == -1) {
			where = " where 1=1 " + where;
		}
		// condition子句自动去头
		if (condition.toLowerCase().startsWith("where ")) {
			condition = condition.substring(6, condition.length());
		}

		// 自动补充and关键字
		if (!condition.toLowerCase().startsWith("and") && !condition.toLowerCase().startsWith("or")) {
			where += " and ";
		}

		return where += " " + condition;
	}

	/**
	 * 智能构建where
	 * @param s
	 * @return
	 */
	public static String buildWhere(String s, String condition) {
		if (condition == null)
			condition = "";
		if (s == null)
			s = "";
		s = s.trim();
		condition = condition.trim();
		if (!s.toLowerCase().endsWith("where") && !condition.toLowerCase().startsWith("where")) {
			s += " where 1=1 ";
		}
		return s + " " + condition;
	}

	public static String buildWhere(String condition) {
		return buildWhere("", condition);
	}

	/**
	 * 格式化SQL去除各种拼接逻辑产生的无意义字符
	 * @param sql
	 */
	public static String formatSql(String sql) {
		// 多个空格替换
		sql = sql.trim();
		sql = sql.replaceAll("\\s+", " ");
		if (sql.endsWith(" where 1=1")) {
			sql = sql.replace(" where 1=1", "");
		}
		sql = sql.replace(" where 1=1 and ", " where ");
		sql = sql.replace(" where 1=1 order ", " order ");
		return sql;
	}

	/**
	 * SQL不换行格式化
	 * @param str
	 * @return
	 */
	public static String notNewLine(String str) {
		str = str.replaceAll("\t|\r|\n", " ");
		str = str.replaceAll("  ", " ");
		return str;
	}

}
