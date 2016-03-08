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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.widget.Toast;

import com.personal.coine.scorpion.jxnuhelper.adapter.NewsListAdapter;
import com.personal.coine.scorpion.jxnuhelper.bean.News;
import com.personal.coine.scorpion.jxnuhelper.biz.INewsBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.NewsBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.INewsView;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/8
 */
public class NewsPresenter {
    private INewsView newsView;
    private INewsBiz newsBiz;

    public NewsPresenter(INewsView newsView) {
        this.newsView = newsView;
        this.newsBiz = new NewsBizImpl();
    }

    public void requestNewsData() {
        newsBiz.getNewsData(newsView.getFragmentContext(), new FindListener<News>() {
            @Override
            public void onSuccess(List<News> list) {
                NewsListAdapter adapter = new NewsListAdapter(newsView.getFragmentContext(), list);
                newsView.getListView().setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(newsView.getFragmentContext(), "错误:" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}