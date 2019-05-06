/**
 * Copyright (c) 2015-2018, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at admin@eova.cn
 */
package com.eova.common.render;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

public class LogRender extends Render {

	private static final String contentType = "text/plain;charset=" + getEncoding();

	private String fileName;
	private String txt;

	public LogRender(String txt, String fileName) {
		if (txt == null)
			throw new IllegalArgumentException("The parameter txt can not be null.");
		this.txt = txt;
		this.fileName = fileName;
	}

	public void render() {
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType(contentType);
			response.setCharacterEncoding(getEncoding());
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, getEncoding()));

			writer = response.getWriter();
			writer.write(txt);
			writer.flush();
		} catch (IOException e) {
			throw new RenderException(e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
