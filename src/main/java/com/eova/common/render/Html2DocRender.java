/**
 * Copyright (c) 2015-2018, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at admin@eova.cn
 */
package com.eova.common.render;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

public class Html2DocRender extends Render {

	private final static String CONTENT_TYPE = "application/msword;charset=" + getEncoding();

    private final String file;
    private final String fileName;

    /**
	 * 渲染文件
	 * 
	 * @param fileName 下载文件名
	 * @param path 模版文件路径
	 * @param paras 模版参数
	 */
    public Html2DocRender(String fileName, String path, HashMap<String, Object> paras) {
        this.fileName = fileName;
        this.file = parseFile(path, paras);
    }

    @Override
    public void render() {
        PrintWriter writer = null;
        try {
            response.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setDateHeader("Expires", 0);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(getEncoding());

            writer = response.getWriter();
            writer.write(file);
            writer.flush();
        } catch (IOException e) {
            throw new RenderException(e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }


    public String parseFile(String path, HashMap<String, Object> params) {
		// String temp = TxtUtil.getTxt(path);

		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("doc");
		Configuration cfg = null;
		try {
			cfg = Configuration.defaultConfiguration();
			cfg.setPlaceholderStart("[");
			cfg.setPlaceholderEnd("]");
			cfg.setCharset("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		Template t = gt.getTemplate(File.separator + path);
		// System.out.println(t.render().toString());

        for (String key : params.keySet()) {
            Object o = params.get(key);
            t.binding(key, o);
        }
        return t.render();
    }

}
