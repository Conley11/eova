/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.eova.common.base.BaseModel;
import com.eova.common.utils.xx;
import com.eova.core.menu.config.MenuConfig;

public class Menu extends BaseModel<Menu> {

	private static final long serialVersionUID = 7072369370299999169L;

	/** 菜单类型-目录 **/
	public static final String TYPE_DIR = "dir";
	/** 菜单类型-自定义 **/
	public static final String TYPE_DIY = "diy";

	public static final Menu dao = new Menu();

	private List<Menu> childList;

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

	public String getBizIntercept() {
		return this.getStr("biz_intercept");
	}

	public MenuConfig getConfig() {
		String json = this.getStr("config");
		if (xx.isEmpty(json)) {
			return null;
		}
		return new MenuConfig(json);
	}

	public void setConfig(MenuConfig config) {
		this.set("config", JSON.toJSONString(config));
	}

	/**
	 * 获取访问URL
	 */
	public String getUrl() {
		String type = this.getStr("type");
		if(type.equals(Menu.TYPE_DIR))
			return "";
		if (type.equals(TYPE_DIY))
			return this.getStr("url");
		return '/' + type + "/list/" + this.getStr("code");
	}

	public Menu findByCode(String code) {
		if (code == null) {
			return null;
		}
		String sql = "select * from eova_menu where code = ?";
		return Menu.dao.queryFisrtByCache(sql, code);
	}

	/**
	 * 获取根节点
	 *
	 * @return
	 */
	public List<Menu> queryRoot() {
		return super.queryByCache("select * from eova_menu where parent_id = 0 order by order_num");
	}

	/**
	 * 获取所有可见菜单
	 *
	 * @return
	 */
	public List<Menu> queryMenu() {
		// TODO MSSQL open为关键字需要替换成,比如 is_open,然后在外面启用兼容转换代码
		String sql = "select id,parent_id,name,iconskip,open,code,type,url from eova_menu where is_hide = 0 order by parent_id,order_num";
		return super.queryByCache(sql);
	}
	
	/**
	 * 是否父节点
	 * @param id
	 * @return
	 */
	public boolean isParent(int id){
		String sql = "select count(*) from eova_menu where parent_id = ?";
		return Menu.dao.isExist(sql, id);
	}
}