/**
 * Copyright (c) 2013-2016, czhang. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at 1623736450@qq.com
 */
package com.eova.cloud;

import com.jfinal.core.Controller;

/**
 * Eova云
 *
 * @author czhang
 * @date 2015-1-6
 */
public class CloudController extends Controller {

    public void index() {
        render("/eova/cloud/index.html");
    }

}