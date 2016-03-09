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
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.core.ApplicationDelegate;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/9
 */
public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initViews();
    }

    private void initViews() {
        ((TextView) findViewById(R.id.user_name)).setText(ApplicationDelegate.getInstance().getCurrentUser().getUsername());
        ((TextView) findViewById(R.id.phone_number)).setText(ApplicationDelegate.getInstance().getCurrentUser().getMobilePhoneNumber());

        findViewById(R.id.row_avadar).setOnClickListener(this);
        findViewById(R.id.row_username).setOnClickListener(this);
        findViewById(R.id.row_sex).setOnClickListener(this);
        findViewById(R.id.row_hometown).setOnClickListener(this);
        findViewById(R.id.row_personal_sign).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.row_avadar:
                break;
            case R.id.row_username:
                break;
            case R.id.row_sex:
                break;
            case R.id.row_hometown:
                break;
            case R.id.row_personal_sign:
                break;
            default:
                break;
        }
    }
}