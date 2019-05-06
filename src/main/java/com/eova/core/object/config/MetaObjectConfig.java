/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.object.config;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.eova.common.utils.xx;

/**
 * 元对象配置
 * 
 * @author czhang
 * 
 */
public class MetaObjectConfig {

	// 视图配置
	private LinkedHashMap<String, TableConfig> view;

	public MetaObjectConfig() {
	}

	public MetaObjectConfig(String s) {
		JSONObject json = JSON.parseObject(s);
		this.view = JSON.parseObject(json.getString("view"), new TypeReference<LinkedHashMap<String, TableConfig>>() {});
	}
	

	public LinkedHashMap<String, TableConfig> getView() {
		return view;
	}

	public void setView(LinkedHashMap<String, TableConfig> view) {
		this.view = view;
	}

	public static void main(String[] args) {
		MetaObjectConfig o = new MetaObjectConfig();
		// {"viewMap":{"users":{"key":"id","value":"id"},"users_exp":{"key":"users_id","value":"id"}}}
		LinkedHashMap<String, TableConfig> v = new LinkedHashMap<>();
		{
			TableConfig tc = new TableConfig();
			tc.setWhereField("id");
			tc.setParamField("id");
			v.put("users", tc);
		}
		{
			TableConfig tc = new TableConfig();
			tc.setWhereField("users_id");
			tc.setParamField("id");
			v.put("users_exp", tc);
		}
		o.setView(v);

		String s = JSONObject.toJSONString(o);
		System.out.println(xx.formatJson(s));
		
		MetaObjectConfig metaObjectConfig = new MetaObjectConfig(s);
		String s1 = JSONObject.toJSONString(metaObjectConfig);
		System.out.println(xx.formatJson(s1));
		
	}


}
