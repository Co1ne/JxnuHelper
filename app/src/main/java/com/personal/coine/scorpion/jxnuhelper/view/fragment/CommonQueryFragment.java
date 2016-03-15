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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.adapter.QueryListViewAdapter;
import com.personal.coine.scorpion.jxnuhelper.view.activity.CourseQueryActivity;
import com.personal.coine.scorpion.jxnuhelper.view.activity.FeeChargeActivity;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/7
 */
public class CommonQueryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_query, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        view.findViewById(R.id.today_weather);
        ListView queryList = (ListView) view.findViewById(R.id.query_choice);
        QueryListViewAdapter adapter = new QueryListViewAdapter(getActivity(), new String[]{"课表查询", "校园卡缴费", "学期考试", "自习室", "成绩查询", "校历查询"}, new int[]{R.drawable.query_class, R.drawable.electric_charge, R.drawable.exam_plan_, R.drawable.self_learn_room, R.drawable.grade_query, R.drawable.school_calendar});
        queryList.setAdapter(adapter);
        queryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(),CourseQueryActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(),FeeChargeActivity.class));
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }
}