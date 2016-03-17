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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.bean.SelfLearningRoom;
import com.personal.coine.scorpion.jxnuhelper.biz.ILearningRoomBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.LearningRoomBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.ILearningRoomView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class LearningRoomPresenter {
    private static final String TAG = LearningRoomPresenter.class.getSimpleName();
    private ILearningRoomView mLearningRoomView;
    private ILearningRoomBiz mLearningRoomBiz;

    public LearningRoomPresenter(ILearningRoomView learningRoomView) {
        this.mLearningRoomView = learningRoomView;
        this.mLearningRoomBiz = new LearningRoomBizImpl();
    }

    public void loadRoomsData() {
        mLearningRoomView.showLoading();
        mLearningRoomBiz.loadRoomsData(mLearningRoomView.loadThisContext(), new FindListener<SelfLearningRoom>() {
            @Override
            public void onSuccess(List<SelfLearningRoom> list) {
                mLearningRoomView.hideLoading();
                List<String> roomName = new ArrayList<String>();
                List<String> location = new ArrayList<String>();
                List<String> roomType = new ArrayList<String>();
                List<Integer> capacity = new ArrayList<Integer>();
                List<String> openTime = new ArrayList<String>();
                List<Integer> restCapacity = new ArrayList<Integer>();

                for (SelfLearningRoom selfLearningRoom : list) {
                    roomName.add(selfLearningRoom.getRoomName());
                    location.add(selfLearningRoom.getLocation());
                    roomType.add(selfLearningRoom.getRoomType());
                    capacity.add(selfLearningRoom.getCapacity());
                    openTime.add(selfLearningRoom.getOpenTime());
                    restCapacity.add(selfLearningRoom.getRestCapacity());
                }
                mLearningRoomView.appendView(roomName, location, roomType, capacity, openTime, restCapacity);
            }

            @Override
            public void onError(int i, String s) {
                mLearningRoomView.hideLoading();
                mLearningRoomView.showError("对不起，查询自习室失败");
                Log.e(TAG, "查询自习室失败" + s);
            }
        });
    }
}