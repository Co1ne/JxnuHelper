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
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.personal.coine.scorpion.jxnuhelper.bean.Academy;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.IUserInfoBiz;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/10
 */
public class UserInfoBizImpl implements IUserInfoBiz {
    @Override
    public void changeUserAvadar(Context context, String userAvadarPath, UploadListener uploadListener) {
        BTPFileResponse response = BmobProFile.getInstance(context).upload(userAvadarPath, uploadListener);

    }

    @Override
    public void loadUserAvadar(final Context context, final Callback callback) {

        final OkHttpClient client = new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(BmobUser.getCurrentUser(context, MyUser.class).getUserAvadarPath()).build();
                client.newCall(request).enqueue(callback);
            }
        }).start();
    }

    @Override
    public void updateUserInfo(Context context, MyUser updateUser, UpdateListener updateListener) {
        updateUser.update(context, BmobUser.getCurrentUser(context, MyUser.class).getObjectId(), updateListener);
    }

    @Override
    public void loadUserAcademy(Context context, String objectId, GetListener<Academy> getListener) {
        BmobQuery<Academy> query = new BmobQuery<>();
        query.getObject(context, objectId, getListener);
    }
}
