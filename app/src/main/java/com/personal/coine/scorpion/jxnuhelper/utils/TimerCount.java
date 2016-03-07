package com.personal.coine.scorpion.jxnuhelper.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.personal.coine.scorpion.jxnuhelper.R;


public class TimerCount extends CountDownTimer {
    private Button button;

    public TimerCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    public TimerCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.bg_corner_button_active);
        button.setText("获取短信验证码");
    }

    @Override
    public void onTick(long remainingTime) {
        button.setBackgroundResource(R.drawable.bg_corner_button_passive);
        button.setClickable(false);
        button.setText(remainingTime / 1000 + "秒后重新获取");
    }
}