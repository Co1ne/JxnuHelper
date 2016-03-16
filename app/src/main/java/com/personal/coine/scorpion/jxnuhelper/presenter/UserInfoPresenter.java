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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.bmob.btp.callback.UploadListener;
import com.personal.coine.scorpion.jxnuhelper.bean.Academy;
import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.IUserInfoBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.UserInfoBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IUserInfoView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
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
    private Bitmap userAvadar;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                userInfoView.getAvadarView().setImageBitmap(userAvadar);
            }
        }
    };

    public UserInfoPresenter(IUserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        this.userInfoBiz = new UserInfoBizImpl();
    }

    public void changeUserAvadar() {
        userInfoView.showLoading();
        userInfoBiz.changeUserAvadar(userInfoView.getThisContext(), userInfoView.getUserAvadarPath(), new UploadListener() {

            @Override
            public void onSuccess(String fileName, String url, BmobFile bmobFile) {
                Log.d(TAG, "文件上传成功：" + fileName + ",可访问的文件地址：" + bmobFile.getUrl());
                MyUser newUser = new MyUser();
                newUser.setUserAvadarPath(bmobFile.getUrl());
                MyUser user = BmobUser.getCurrentUser(userInfoView.getThisContext(), MyUser.class);
                newUser.update(userInfoView.getThisContext(), user.getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "更新用户信息成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.d(TAG, "更新用户信息失败," + s);
                    }
                });

                bmobFile.loadImage(userInfoView.getThisContext(), userInfoView.getAvadarView());
                userInfoView.hideLoading();
            }

            @Override
            public void onProgress(int progress) {
                userInfoView.showLoadingProgress(progress);
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "error:" + s);
                userInfoView.hideLoading();
            }
        });
    }

    public void loadUserAvadar() {
        if (BmobUser.getCurrentUser(userInfoView.getThisContext(), MyUser.class).getUserAvadarPath() != null) {
            userInfoBiz.loadUserAvadar(userInfoView.getThisContext(), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                    Toast.makeText(userInfoView.getThisContext(), "获取头像失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        InputStream is = response.body().byteStream();
                        userAvadar = BitmapFactory.decodeStream(is);
                        is.close();
                        Message msg = new Message();
                        msg.what = 0x123;
                        mHandler.sendMessage(msg);
                        Log.d(TAG, response.body().toString());
                    } else {
                        Toast.makeText(userInfoView.getThisContext(), "获取头像失败", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void updateUserInfo() {
        MyUser updateUser = new MyUser();
        if (userInfoView.getUserName() != null && !userInfoView.getUserName().equals("")) {
            updateUser.setUsername(userInfoView.getUserName());
        }
        if (userInfoView.getSex() != null && !userInfoView.getSex().equals("")) {
            updateUser.setSex(userInfoView.getSex());
        }
        if (userInfoView.getProvince() != null && !userInfoView.getProvince().equals("")) {
            updateUser.setProvince(userInfoView.getProvince());
        }
        if (userInfoView.getCity() != null && !userInfoView.getCity().equals("")) {
            updateUser.setCity(userInfoView.getCity());
        }
        if (userInfoView.getDistrict() != null && !userInfoView.getDistrict().equals("")) {
            updateUser.setDistrict(userInfoView.getDistrict());
        }
        if (userInfoView.getPersonalSign() != null && !userInfoView.getPersonalSign().equals("")) {
            updateUser.setPersonalSign(userInfoView.getPersonalSign());
        }
        userInfoBiz.updateUserInfo(userInfoView.getThisContext(), updateUser, new UpdateListener() {
            @Override
            public void onSuccess() {
                userInfoView.refreshViews();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(userInfoView.getThisContext(), "更新用户信息失败", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "更新用户信息失败," + s);
            }
        });
    }

    public void loadUserAcademy() {
        userInfoBiz.loadUserAcademy(userInfoView.getThisContext(), BmobUser.getCurrentUser(userInfoView.getThisContext(), MyUser.class).getStuAcademy() == null ? "" : BmobUser.getCurrentUser(userInfoView.getThisContext(), MyUser.class).getStuAcademy().getObjectId(), new GetListener<Academy>() {
            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(userInfoView.getThisContext(), "获取您的学院信息好像失败了...", Toast.LENGTH_SHORT).show();
                Log.e(TAG, i + "<---->获取学院信息失败:" + s);
            }

            @Override
            public void onSuccess(Academy academy) {
                userInfoView.getAcademyTextView().setText(academy.getAcademyName() + "," + academy.getClassName());
            }
        });
    }

    public void loadUserCardNumber() {
        userInfoBiz.loadUserCardNumber(userInfoView.getThisContext(), BmobUser.getCurrentUser(userInfoView.getThisContext(), MyUser.class), new FindListener<CampusCard>() {
            @Override
            public void onSuccess(List<CampusCard> list) {
                userInfoView.showUserCardNumber(list.get(0));
            }

            @Override
            public void onError(int i, String s) {
                userInfoView.showError("对不起，获取校园卡号失败");
                Log.e(TAG, "获取校园卡号失败:" + s);
            }
        });
    }

    public void updateCardNumber() {
        userInfoBiz.updateCardNumber(userInfoView.getThisContext(), userInfoView.getCard(), userInfoView.getCardNumber(), new UpdateListener() {
            @Override
            public void onSuccess() {
                loadUserCardNumber();
            }

            @Override
            public void onFailure(int i, String s) {
                userInfoView.showError("对不起，更新校园卡号失败");
                Log.e(TAG, "更新卡失败" + s);
            }
        });
    }
}
