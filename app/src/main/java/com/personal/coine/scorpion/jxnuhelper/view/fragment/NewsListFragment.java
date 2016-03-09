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
package com.personal.coine.scorpion.jxnuhelper.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.presenter.NewsPresenter;
import com.personal.coine.scorpion.jxnuhelper.view.INewsView;
import com.personal.coine.scorpion.jxnuhelper.view.activity.NewsDetailActivity;
import com.personal.coine.scorpion.jxnuhelper.view.refreshview.HitBlockRefreshView;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class NewsListFragment extends Fragment implements INewsView {

    private HitBlockRefreshView refreshView;
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private ListView newsList;
    private KProgressHUD loadingProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_news, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        loadingProgress = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载数据...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        refreshView = (HitBlockRefreshView) view.findViewById(R.id.refresh_hit_block);
        newsList = (ListView) view.findViewById(R.id.news_list);
        newsPresenter.requestNewsData();
        refreshView.setOnRefreshListener(new HitBlockRefreshView.HitBlockRefreshListener() {
            @Override
            public void onRefreshing() {
                newsPresenter.requestNewsData();
            }
        });
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newsPresenter.goDetail();
            }
        });
    }


    @Override
    public HitBlockRefreshView getRefreshView() {
        return refreshView;
    }

    @Override
    public ListView getListView() {
        return newsList;
    }

    @Override
    public void showLoading() {
        loadingProgress.show();
    }

    @Override
    public void hideLoading() {
        loadingProgress.dismiss();
    }

    @Override
    public Context getFragmentContext() {
        return getContext();
    }
}