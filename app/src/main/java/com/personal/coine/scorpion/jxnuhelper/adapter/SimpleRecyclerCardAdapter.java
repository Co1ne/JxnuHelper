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
 * 2016/3/12  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personal.coine.scorpion.jxnuhelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/12
 */
public class SimpleRecyclerCardAdapter extends RecyclerView.Adapter<SimpleRecyclerCardAdapter.SimpleCardViewHolder> {
    private final List<String> mExamTitle = new ArrayList<>();
    private final List<String> mExamDate = new ArrayList<>();
    private final List<String> mExamContent = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemActionListener mOnItemActionListener;

    public SimpleRecyclerCardAdapter(Context context, List<String> examTitleList, List<String> examDateList, List<String> examContentList) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.mExamTitle.addAll(examTitleList);
        this.mExamDate.addAll(examDateList);
        this.mExamContent.addAll(examContentList);
    }

    @Override
    public SimpleCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = mInflater.inflate(R.layout.exam_recycler_item, null);
        SimpleCardViewHolder simpleCardViewHolder = new SimpleCardViewHolder(inflateView);
        simpleCardViewHolder.setIsRecyclable(true);
        return simpleCardViewHolder;
    }

    @Override
    public int getItemCount() {
        return mExamTitle.size();
    }

    @Override
    public void onBindViewHolder(final SimpleCardViewHolder holder, int position) {
        holder.examTitle.setText(mExamTitle.get(position));
        holder.examDate.setText(mExamDate.get(position));
        holder.examContent.setText(mExamContent.get(position));
        if (mOnItemActionListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //注意这里必须使用viewHolder.getAdapterPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    mOnItemActionListener.onItemClickListener(v, holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    return mOnItemActionListener.onItemLongClickListener(v, holder.getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }

    public interface OnItemActionListener {
        void onItemClickListener(View view, int position);

        Boolean onItemLongClickListener(View view, int position);
    }

    class SimpleCardViewHolder extends RecyclerView.ViewHolder {
        private TextView examTitle;
        private TextView examDate;
        private TextView examContent;

        public SimpleCardViewHolder(View itemView) {
            super(itemView);
            examTitle = (TextView) itemView.findViewById(R.id.exam_title);
            examDate = (TextView) itemView.findViewById(R.id.exam_date);
            examContent = (TextView) itemView.findViewById(R.id.exam_content);
        }
    }
}