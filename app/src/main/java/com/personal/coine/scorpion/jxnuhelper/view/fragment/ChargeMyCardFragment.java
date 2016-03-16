package com.personal.coine.scorpion.jxnuhelper.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.presenter.ChargePresenter;
import com.personal.coine.scorpion.jxnuhelper.view.IChargeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;


public class ChargeMyCardFragment extends Fragment implements IChargeView {
    private ChargePresenter chargePresenter = new ChargePresenter(this);
    private Integer chargeSum;
    private TextView userBalance;
    private TextView cardNumber;
    private CampusCard currentCardInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge_my_card, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        chargePresenter.loadCardInfo();
        cardNumber = (TextView) view.findViewById(R.id.my_card_number);
        TextView userName = (TextView) view.findViewById(R.id.my_name);
        userBalance = (TextView) view.findViewById(R.id.user_balance);
        userName.setText(BmobUser.getCurrentUser(getContext(), MyUser.class).getUsername());
        final GridView chargeAmount = (GridView) view.findViewById(R.id.charge_amount);
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
        return BmobUser.getCurrentUser(getContext(), MyUser.class);
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
        cardNumber.setText(campusCard.getCardNumber());
        userBalance.setText("余额:    " + campusCard.getCardBalance());
    }

    @Override
    public CampusCard loadCardInfo() {
        return currentCardInfo;
    }
}
