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
 * 2016/3/6  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.ILoginBiz;

import cn.bmob.v3.listener.LogInListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/6
 */
public class LoginBizImpl implements ILoginBiz {
    @Override
    public void login(Context context, String phoneNumber, String password, LogInListener<MyUser> logInListener) {
        MyUser.loginByAccount(context, phoneNumber, password, logInListener);
    }
}