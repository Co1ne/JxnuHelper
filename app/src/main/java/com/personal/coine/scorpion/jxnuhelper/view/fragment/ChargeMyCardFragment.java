package com.personal.coine.scorpion.jxnuhelper.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.personal.coine.scorpion.jxnuhelper.R;


public class ChargeMyCardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge_my_card, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

    }
}
