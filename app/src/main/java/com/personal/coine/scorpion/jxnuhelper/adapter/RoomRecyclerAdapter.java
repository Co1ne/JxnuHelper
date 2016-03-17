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
package com.personal.coine.scorpion.jxnuhelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.RoomViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mRoomName;
    private List<String> mLocation;
    private List<String> mRoomType;
    private List<Integer> mCapacity;
    private List<String> mOpenTime;
    private List<Integer> mRestCapacity;

    public RoomRecyclerAdapter(Context context, List<String> roomName, List<String> location, List<String> roomType, List<Integer> capacity, List<String> openTime, List<Integer> restCapacity) {
        this.mInflater = LayoutInflater.from(context);
        this.mRoomName = roomName;
        this.mLocation = location;
        this.mRoomType = roomType;
        this.mCapacity = capacity;
        this.mOpenTime = openTime;
        this.mRestCapacity = restCapacity;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RoomViewHolder(mInflater.inflate(R.layout.room_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        holder.roomNameText.setText("名字:" + mRoomName.get(position));
        holder.locationText.setText("位置:" + mLocation.get(position));
        holder.roomTypeText.setText("类型" + mRoomType.get(position));
        holder.capacityText.setText("共:" + mCapacity.get(position) + "个位置");
        holder.openTimeText.setText("开放时间" + mOpenTime.get(position));
        holder.restCapacityText.setText("剩余:" + mRestCapacity.get(position) + "个位置");
    }

    @Override
    public int getItemCount() {
        return mRoomName.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView roomNameText;
        private TextView locationText;
        private TextView roomTypeText;
        private TextView capacityText;
        private TextView openTimeText;
        private TextView restCapacityText;

        public RoomViewHolder(View itemView) {
            super(itemView);
            roomNameText = (TextView) itemView.findViewById(R.id.room_name);
            locationText = (TextView) itemView.findViewById(R.id.location);
            roomTypeText = (TextView) itemView.findViewById(R.id.room_type);
            capacityText = (TextView) itemView.findViewById(R.id.capacity);
            openTimeText = (TextView) itemView.findViewById(R.id.open_time);
            restCapacityText = (TextView) itemView.findViewById(R.id.rest_capacity);
        }
    }
}