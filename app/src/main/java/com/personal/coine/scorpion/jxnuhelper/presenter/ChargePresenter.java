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
package com.personal.coine.scorpion.jxnuhelper.presenter;

import android.util.Log;

import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.biz.IChargeBiz;
import com.personal.coine.scorpion.jxnuhelper.biz.impl.ChargeBizImpl;
import com.personal.coine.scorpion.jxnuhelper.view.IChargeView;

import java.util.List;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ChargePresenter {
    private static final String TAG = ChargePresenter.class.getSimpleName();
    private IChargeView mChargeView;
    private IChargeBiz mChargeBiz;

    public ChargePresenter(IChargeView chargeView) {
        this.mChargeView = chargeView;
        this.mChargeBiz = new ChargeBizImpl();
    }

    public void doCharge() {
        mChargeBiz.doCharge(mChargeView.loadThisContext(), mChargeView.loadCardInfo(), mChargeView.loadChargeUser(), mChargeView.loadChargeSum(), new UpdateListener() {
            @Override
            public void onSuccess() {
                mChargeView.showSuccess("充值成功");
                loadCardInfo();
                mChargeBiz.addChargeLog(mChargeView.loadThisContext(), mChargeView.loadChargeSum(), mChargeView.loadCardInfo());
            }

            @Override
            public void onFailure(int i, String s) {
                mChargeView.showError("对不起,充值失败");
                Log.e(TAG, "充值失败:" + s);
            }
        });
    }

    public void loadCardInfo() {
        mChargeBiz.loadCardInfo(mChargeView.loadThisContext(), mChargeView.loadChargeUser(), new FindListener<CampusCard>() {
            @Override
            public void onSuccess(List<CampusCard> list) {
                if (list != null && list.size() > 0) {
                    mChargeView.showCardInfo(list.get(0));
                }
            }

            @Override
            public void onError(int i, String s) {
                mChargeView.showError("对不起,查询卡号失败");
                Log.e(TAG, "查询卡号失败:" + s);
            }
        });
    }
}