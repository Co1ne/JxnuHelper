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
 * 2016/3/7  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class QueryListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private String[] titles;
    private int[] resIds;

    public QueryListViewAdapter(Context context, String[] titles, int[] resIds) {
        this.titles = titles;
        this.resIds = resIds;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.mInflater.inflate(R.layout.query_item, null);
            holder.itemIcon = (ImageView) convertView.findViewById(R.id.query_item_image);
            holder.itemTitle = (TextView) convertView.findViewById(R.id.query_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemIcon.setImageResource(resIds[position]);
        holder.itemTitle.setText(titles[position]);
        return convertView;
    }

    class ViewHolder {
        private ImageView itemIcon;
        private TextView itemTitle;
    }
}