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
 * 2016/3/17  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.adapter.RoomRecyclerAdapter;
import com.personal.coine.scorpion.jxnuhelper.presenter.LearningRoomPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.ILearningRoomView;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class RestSelfLearningRoomActivity extends AppCompatActivity implements ILearningRoomView {
    private KProgressHUD loadingProgress;
    private LearningRoomPresenter learningRoomPresenter = new LearningRoomPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_self_learning_room);
        initActionBar();
        loadingProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        initData();
    }

    private void initData() {
        learningRoomPresenter.loadRoomsData();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("自习室列表");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        loadingProgress.show();
    }

    @Override
    public Context loadThisContext() {
        return this;
    }

    @Override
    public void hideLoading() {
        loadingProgress.dismiss();
    }

    @Override
    public void appendView(List<String> roomName, List<String> location, List<String> roomType, List<Integer> capacity, List<String> openTime, List<Integer> restCapacity) {
        RecyclerView roomList = (RecyclerView) findViewById(R.id.room_list);
        // 设置LinearLayoutManager
        roomList.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        roomList.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        roomList.setHasFixedSize(true);
        RoomRecyclerAdapter adapter = new RoomRecyclerAdapter(this, roomName, location, roomType, capacity, openTime, restCapacity);
        roomList.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(RestSelfLearningRoomActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}