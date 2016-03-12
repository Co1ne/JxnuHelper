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
 * 2016/3/9  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.presenter.UserInfoPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.IUserInfoView;
import com.personal.coine.scorpion.jxnuhelper.view.activity.MyInfoActivity;

import cn.bmob.v3.BmobUser;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/9
 */
public class MineFragment extends Fragment implements View.OnClickListener, IUserInfoView {
    private ImageView userAvadarImg;
    private UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ((TextView) view.findViewById(R.id.user_name_text)).setText(BmobUser.getCurrentUser(getContext(), MyUser.class).getUsername());
        userAvadarImg = (ImageView) view.findViewById(R.id.user_avadar_img);
        userInfoPresenter.loadUserAvadar();
        view.findViewById(R.id.mine_page_row1).setOnClickListener(this);
        view.findViewById(R.id.mine_page_row2).setOnClickListener(this);
        view.findViewById(R.id.mine_page_row3).setOnClickListener(this);
        view.findViewById(R.id.mine_page_row4).setOnClickListener(this);
        view.findViewById(R.id.mine_page_row5).setOnClickListener(this);
        view.findViewById(R.id.mine_page_row6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_page_row1:
                startActivity(new Intent(getContext(), MyInfoActivity.class));
                break;
            case R.id.mine_page_row2:
                break;
            case R.id.mine_page_row3:
                break;
            case R.id.mine_page_row4:
                break;
            case R.id.mine_page_row5:
                break;
            case R.id.mine_page_row6:
                break;
        }
    }

    @Override
    public Context getThisContext() {
        return getContext();
    }

    @Override
    public String getUserAvadarPath() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadingProgress(int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public ImageView getAvadarView() {
        return userAvadarImg;
    }
}