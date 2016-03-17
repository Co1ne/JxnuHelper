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
 * 2016/3/17  1.0     huangwei    Creation File
 */
package com.personal.coine.scorpion.jxnuhelper.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.github.tibolte.agendacalendarview.render.DefaultEventRenderer;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.personal.coine.scorpion.jxnuhelper.R;
import com.personal.coine.scorpion.jxnuhelper.bean.SchoolCalendar;
import com.personal.coine.scorpion.jxnuhelper.presenter.SchoolCalendarPresenter;
import com.personal.coine.scorpion.jxnuhelper.utils.DrawableCalendarEvent;
import com.personal.coine.scorpion.jxnuhelper.view.ICalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Description:
 *
 * @author huangwei
 *         Date 2016/3/17
 */
public class SchoolCalendarActivity extends AppCompatActivity implements CalendarPickerController, ICalendarView {
    private static final String TAG = SchoolCalendarActivity.class.getSimpleName();
    private SchoolCalendarPresenter calendarPresenter = new SchoolCalendarPresenter(this);

    private ActionBar actionBar;
    private KProgressHUD loadingProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_calendar);
        initActionBar();
        initCalendar();
    }

    private void initCalendar() {
        loadingProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("正在加载...").setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
        calendarPresenter.loadEventList();
    }

    private void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("校园日历");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        actionBar.setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CHINA));
    }

    @Override
    public void showLoading() {
        loadingProgress.show();
    }

    @Override
    public Context loadThisContext() {
        return this;
    }

    @Override
    public void hideLoading() {
        loadingProgress.dismiss();
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(SchoolCalendarActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void renderDate(List<SchoolCalendar> calendarList) {
        AgendaCalendarView schoolCalendarView = (AgendaCalendarView) findViewById(R.id.school_calendar_view);
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);
        List<CalendarEvent> eventList = new ArrayList<>();
        try {
            for (int i = 0; i < calendarList.size(); i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Calendar startTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
                startTime.setTime(sdf.parse(calendarList.get(i).getStartDate().getDate()));
                endTime.setTime(sdf.parse(calendarList.get(i).getEndDate().getDate()));
                int backColor[] = new int[]{ContextCompat.getColor(this, R.color.orange_dark), ContextCompat.getColor(this, R.color.yellow), ContextCompat.getColor(this, R.color.blue_dark)};
                DrawableCalendarEvent event = new DrawableCalendarEvent(calendarList.get(i).getEventTitle(), calendarList.get(i).getEventContent(), calendarList.get(i).getEventLocation(),
                        backColor[i % 3], startTime, endTime, calendarList.get(i).getAllDay(), android.R.drawable.ic_dialog_info);
                eventList.add(event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        schoolCalendarView.init(eventList, minDate, maxDate, Locale.CHINA, this);
        schoolCalendarView.addEventRenderer(new DefaultEventRenderer());
    }
}