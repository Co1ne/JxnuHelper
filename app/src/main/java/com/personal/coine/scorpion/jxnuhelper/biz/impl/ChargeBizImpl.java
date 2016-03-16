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
package com.personal.coine.scorpion.jxnuhelper.biz.impl;

import android.content.Context;

import com.personal.coine.scorpion.jxnuhelper.bean.CampusCard;
import com.personal.coine.scorpion.jxnuhelper.bean.ChargeLog;
import com.personal.coine.scorpion.jxnuhelper.bean.MyUser;
import com.personal.coine.scorpion.jxnuhelper.biz.IChargeBiz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/16
 */
public class ChargeBizImpl implements IChargeBiz {
    @Override
    public void doCharge(Context context,CampusCard currentCard, MyUser user, Integer chargeSum, UpdateListener listener) {
        CampusCard newCampusCard = new CampusCard();
        newCampusCard.setValue("cardBalance",currentCard.getCardBalance()+chargeSum);
        newCampusCard.update(context,currentCard.getObjectId(),listener);
    }

    @Override
    public void loadCardInfo(Context context, MyUser queryUser, FindListener<CampusCard> findListener) {
        BmobQuery<CampusCard> query = new BmobQuery<>();
        query.addWhereEqualTo("owner", queryUser);
        query.findObjects(context, findListener);
    }

    @Override
    public void addChargeLog(Context context,Integer chargeSum,CampusCard campusCard) {
        ChargeLog log = new ChargeLog();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String time = format.format(date);
        log.setChargeDate(BmobDate.createBmobDate("yyyy-MM-dd HH:mm:ss", time));
        log.setChargeSum(chargeSum);
        log.setCardInfo(campusCard);
        log.save(context);
    }
}