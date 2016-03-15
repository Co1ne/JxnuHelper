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
 * 2016/3/13  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.view.fragment.ChargeMyCardFragment;

/**
 * Description:校园卡查询、缴费￥￥￥
 *
 * @author huangwei
 *         Date 2016/3/13
 */
public class FeeChargeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_charge);
        initActionBar();
        initViews();
    }

    private void initViews() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.my_card, ChargeMyCardFragment.class)
                .add(R.string.classmate_card, ChargeMyCardFragment.class)
                .create());
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.charge_view_pager);
        mainViewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.fee_group);
        viewPagerTab.setViewPager(mainViewPager);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("校园卡充值");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
