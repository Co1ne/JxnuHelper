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
package com.personal.coine.scorpion.jxnuhelper.biz;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public interface IChargeBiz {
    void doCharge(Context context,CampusCard currentCard, MyUser user, Integer chargeSum, UpdateListener listener);

    void loadCardInfo(Context context, MyUser queryUser, FindListener<CampusCard> findListener);

    void addChargeLog(Context context,Integer chargeSum,CampusCard campusCard);
}