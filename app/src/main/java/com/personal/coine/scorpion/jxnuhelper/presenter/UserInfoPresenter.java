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
 * 2016/3/10  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.biz.IUserInfoBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.UserInfoBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IUserInfoView;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/10
 */
public class UserInfoPresenter {
    private static final String TAG = UserInfoPresenter.class.getSimpleName();
    private IUserInfoView userInfoView;
    private IUserInfoBiz userInfoBiz;

    public UserInfoPresenter(IUserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        this.userInfoBiz = new UserInfoBizImpl();
    }

    public void changeUserAvadar(){
        userInfoView.showLoading();
        userInfoBiz.changeUserAvadar(userInfoView.getThisContext(), userInfoView.getUserAvadar(), new UpdateListener() {
            @Override
            public void onSuccess() {
                userInfoView.hideLoading();
                Log.d(TAG,"success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG,"error:"+s);
                userInfoView.hideLoading();
            }
        });
    }
}
