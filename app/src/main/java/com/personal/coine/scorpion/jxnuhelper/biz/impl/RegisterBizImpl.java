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
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.IRegisterBiz;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Description:注册业务
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class RegisterBizImpl implements IRegisterBiz {
    @Override
    public void requestSMSCode(Context context, String phoneNumber, RequestSMSCodeListener smsCodeListener) {
        BmobSMS.requestSMSCode(context, phoneNumber, "注册短信", smsCodeListener);
    }

    @Override
    public void signAndLogin(Context context, String phoneNumber, String password, String SMSCode , SaveListener saveListener) {
        MyUser newUser = new MyUser();
        newUser.setMobilePhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.signOrLogin(context,SMSCode, saveListener);
    }
}