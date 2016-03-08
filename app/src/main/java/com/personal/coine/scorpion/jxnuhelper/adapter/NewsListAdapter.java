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
 * 2016/3/8  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.News;

import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/8
 */
public class NewsListAdapter extends BaseAdapter {

    private List<News> mNewsList;
    private LayoutInflater mInflater;

    public NewsListAdapter(Context context, List<News> newsList) {
        this.mNewsList = newsList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
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
            convertView = this.mInflater.inflate(R.layout.news_list_item, null);
            holder.newsTitleText = (TextView) convertView.findViewById(R.id.news_title_text);
            holder.newsCategoryText = (TextView) convertView.findViewById(R.id.news_category_text);
            holder.newsDateText = (TextView) convertView.findViewById(R.id.news_date_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.newsTitleText.setText(mNewsList.get(position).getNewsTitle());
        holder.newsCategoryText.setText(mNewsList.get(position).getNewsCategory());
        holder.newsDateText.setText("发布于:" + mNewsList.get(position).getNewsDate().getDate());
        return convertView;
    }

    class ViewHolder {
        private TextView newsTitleText;
        private TextView newsCategoryText;
        private TextView newsDateText;
    }
}