/**
 * Copyright (c) 2015-2018, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at admin@eova.cn
 */
package com.eova.common.render;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.eova.common.utils.excel.ExcelUtil;
import com.eova.model.MetaField;
import com.eova.model.MetaObject;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

/**
 * 
 * @author 周磊 (zhouleib1412@gmail.com)
 *
 */
public class XlsRender extends Render {

	private final static String CONTENT_TYPE = "application/msexcel;charset=" + getEncoding();

	private final MetaObject object;
	private final List<MetaField> items;
	private final List<Record> data;

	private final String fileName;

	public XlsRender(List<Record> data, List<MetaField> items, MetaObject object) {
		this.data = data;
		this.items = items;
		this.object = object;

		fileName = object.getStr("name") + ".xls";
	}

	@Override
	public void render() {
		response.reset();
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, getEncoding()));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType(CONTENT_TYPE);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			ExcelUtil.createExcel(os, data, items, object);
		} catch (Exception e) {
			throw new RenderException(e);
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				LogKit.error(e.getMessage(), e);
			}

		}
	}

}
