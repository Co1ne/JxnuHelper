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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.ILoginBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.LoginBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.ILoginView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/6
 */
public class LoginPresenter {
    private ILoginBiz loginBiz;
    private ILoginView userLoginView;

    public LoginPresenter(ILoginView userLoginView) {
        this.userLoginView = userLoginView;
        loginBiz = new LoginBizImpl();
    }

    public void login() {
        userLoginView.showLoading();
        loginBiz.login(userLoginView.getContext(), userLoginView.getPhoneNumber(), userLoginView.getPassword(), new LogInListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                userLoginView.hideLoading();
                if (user != null) {
                    userLoginView.toMainActivity();
                } else {
                    userLoginView.showFailedError(e.getMessage());
                }
            }
        });
    }
}