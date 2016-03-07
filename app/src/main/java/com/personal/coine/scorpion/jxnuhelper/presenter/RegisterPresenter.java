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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.biz.IRegisterBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.RegisterBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IRegisterView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class RegisterPresenter {
    private static final String TAG = RegisterPresenter.class.getSimpleName();
    private IRegisterBiz registerBiz;
    private IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        registerBiz = new RegisterBizImpl();
    }

    public void requestSMSCode() {
        registerBiz.requestSMSCode(registerView.getContext(), registerView.getPhoneNumber(), new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "短信id:" + smsId);
                } else {
                    registerView.showFailedError(e.getMessage());
                }
            }
        });
    }

    public void signAndLogin() {
        registerBiz.signAndLogin(registerView.getContext(), registerView.getPhoneNumber(), registerView.getPassword(), registerView.getSMSCode(), new SaveListener() {
            @Override
            public void onSuccess() {
                registerView.loginAndToMain();
            }

            @Override
            public void onFailure(int i, String s) {
                registerView.showFailedError(s);
            }
        });
    }
}