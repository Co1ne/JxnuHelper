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
 * 2016/3/16  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.presenter.ChargePresenter;
import com.personal.coine.scorpion.jxnuhelper.view.IChargeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ChargeOthersCardFragment extends Fragment implements IChargeView {
    private static final String TAG = ChargeOthersCardFragment.class.getSimpleName();
    private KProgressHUD queryProgress;
    private MyUser chargeUser;
    private Integer chargeSum;
    private ChargePresenter chargePresenter = new ChargePresenter(this);
    private TextView othersBalance;
    private CampusCard currentCardInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge_others_card, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        final TextView userName = (TextView) view.findViewById(R.id.others_name);
        othersBalance = (TextView) view.findViewById(R.id.others_balance);
        final AppCompatEditText userInputCardNumber = (AppCompatEditText) view.findViewById(R.id.et_user_card_number);
        userInputCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 10) {
                    queryProgress = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在查询用户...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
                    BmobQuery<CampusCard> query = new BmobQuery<CampusCard>();
                    query.addWhereEqualTo("cardNumber", s.toString());
                    query.findObjects(getContext(), new FindListener<CampusCard>() {
                        @Override
                        public void onSuccess(List<CampusCard> list) {

                            chargeUser = list.get(0).getOwner();
                            BmobQuery<MyUser> userQuery = new BmobQuery<MyUser>();
                            userQuery.addWhereEqualTo("objectId", chargeUser.getObjectId());
                            userQuery.findObjects(getContext(), new FindListener<MyUser>() {
                                @Override
                                public void onSuccess(List<MyUser> list) {
                                    queryProgress.dismiss();
                                    chargeUser = list.get(0);
                                    userName.setText(chargeUser.getUsername());
                                    chargePresenter.loadCardInfo();
                                }

                                @Override
                                public void onError(int i, String s) {
                                    queryProgress.dismiss();
                                    Log.e(TAG, "获取用户信息失败" + s);
                                    Toast.makeText(getContext(), "对不起，查询用户失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(int i, String s) {
                            queryProgress.dismiss();
                            Toast.makeText(getContext(), "对不起,查询失败", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "查询用户失败" + s);
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        GridView chargeAmount = (GridView) view.findViewById(R.id.others_charge_amount);
        List<Map<String, Object>> dataList = new ArrayList<>();
        final String[] titleArray = new String[]{"10元", "20元", "30元", "50元", "100元", "200元", "300元", "500元"};
        String[] contentArray = new String[]{"售价:10.00元", "售价:20.00元", "售价:29.94元", "售价:49.85元", "售价:99.75元", "售价:199.60元", "售价:299.00元", "售价:498.00元"};
        for (int i = 0; i < titleArray.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", titleArray[i]);
            map.put("content", contentArray[i]);
            dataList.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(), dataList, R.layout.grid_item, new String[]{"title", "content"}, new int[]{R.id.charge_title, R.id.charge_content});
        chargeAmount.setAdapter(adapter);
        chargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("确认充值").setMessage("充值" + titleArray[position] + "?").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chargeSum = Integer.valueOf(titleArray[position].substring(0, (titleArray[position].length() - 1)));
                        chargePresenter.doCharge();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    @Override
    public MyUser loadChargeUser() {
        return chargeUser;
    }

    @Override
    public Integer loadChargeSum() {
        return chargeSum;
    }

    @Override
    public Context loadThisContext() {
        return getContext();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCardInfo(CampusCard campusCard) {
        currentCardInfo = campusCard;
        othersBalance.setText("余额:  " + campusCard.getCardBalance());
    }

    @Override
    public CampusCard loadCardInfo() {
        return currentCardInfo;
    }
}