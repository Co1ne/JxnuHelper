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
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private final List<String> mDataSource = new ArrayList<>();
    private final List<Bitmap> mImageSource = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemActionListener mOnItemActionListener;

    public SimpleRecyclerCardAdapter(Context context, List<String> dataSource, List<Bitmap> bitmapList) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.mDataSource.addAll(dataSource);
        mImageSource.addAll(bitmapList);
    }

    @Override
    public SimpleCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = mInflater.inflate(R.layout.recycler_card_item, null);
        SimpleCardViewHolder simpleCardViewHolder = new SimpleCardViewHolder(inflateView);
        simpleCardViewHolder.setIsRecyclable(true);
        return simpleCardViewHolder;
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    @Override
    public void onBindViewHolder(final SimpleCardViewHolder holder, int position) {
        holder.descriptionText.setText(mDataSource.get(position));
        holder.memoryImage.setImageBitmap(mImageSource.get(position));
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
        private TextView descriptionText;
        private ImageView memoryImage;

        public SimpleCardViewHolder(View itemView) {
            super(itemView);
            descriptionText = (TextView) itemView.findViewById(R.id.item_description);
            memoryImage = (ImageView) itemView.findViewById(R.id.item_memory_img);
        }
    }
}