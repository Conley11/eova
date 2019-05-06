/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.core.menu.config;

import java.util.List;

/**
 * 菜单表树配置
 * 
 * @author czhang
 * 
 */
public class ChartConfig {
	// 报表类型:1=折线,2=柱状,3=饼图
	private int type = 1;
	// X轴
	private String x;
	// Y轴单位
	private String yunit;
	// Y轴
	private List<String> y;
	// Y轴名称
	private List<String> ycn;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getYunit() {
		return yunit;
	}
	public void setYunit(String yunit) {
		this.yunit = yunit;
	}
	public List<String> getY() {
		return y;
	}
	public void setY(List<String> y) {
		this.y = y;
	}
	public List<String> getYcn() {
		return ycn;
	}
	public void setYcn(List<String> ycn) {
		this.ycn = ycn;
	}
	
}