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

import com.bmob.btp.callback.DownloadListener;
import com.bmob.btp.callback.UploadListener;
import com.personal.coine.scorpion.jxnuhelper.biz.IUserInfoBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.UserInfoBizImpl;
import com.personal.coine.scorpion.jxnuhelper.core.ApplicationDelegate;
import com.personal.coine.scorpion.jxnuhelper.view.IUserInfoView;

import cn.bmob.v3.datatype.BmobFile;

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
        userInfoBiz.changeUserAvadar(userInfoView.getThisContext(), userInfoView.getUserAvadarPath(), new UploadListener() {

            @Override
            public void onSuccess(String fileName, String url, BmobFile bmobFile) {
                Log.d(TAG, "文件上传成功：" + fileName + ",可访问的文件地址：" + bmobFile.getUrl());
                ApplicationDelegate.getInstance().getCurrentUser().setUserAvadarName(fileName);
                bmobFile.loadImage(userInfoView.getThisContext(), userInfoView.getAvadarView());
                userInfoView.hideLoading();
            }

            @Override
            public void onProgress(int progress) {
                userInfoView.showLoadingProgress(progress);
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG,"error:"+s);
                userInfoView.hideLoading();
            }
        });
    }

    public void loadUserAvadar() {
        userInfoBiz.loadUserAvadar(userInfoView.getThisContext(), new DownloadListener() {
            @Override
            public void onSuccess(String fullPath) {

            }

            @Override
            public void onProgress(String s, int i) {

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
