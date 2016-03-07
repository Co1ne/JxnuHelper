/*
 * Copyright(c) Runsdata Technologies Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Runsdata
 * Technologies Co., Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Runsdata.
 * For more information about Runsdata, welcome to http://www.runsdata.com
 *
 * Revision History
 * Date     Version     Name        Description
 * 2016/3/7  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.core;

import cn.bmob.v3.BmobUser;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class ApplicationDelegate {
    private static ApplicationDelegate instance = new ApplicationDelegate();
    private BmobUser currentUser;

    public ApplicationDelegate() {
    }

    public static ApplicationDelegate getInstance() {
        return instance;
    }

    public BmobUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(BmobUser currentUser) {
        this.currentUser = currentUser;
    }
}