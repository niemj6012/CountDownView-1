package com.example.tree.countdowntextview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity
        extends AppCompatActivity
{

    private TextView         mTv;
    private MyCountDownTimer mDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        //按照分秒单位来进行递减
        mDownTimer = new MyCountDownTimer(10000, 10);
        //mDownTimer.start();
    }

    /**
     * 开启倒计时
     * @param v
     */
    public void startCountDown(View v) {
        mDownTimer.start();
    }


    /**
     * 关闭倒计时
     * @param v
     */
    public void stopCountDown(View v) {
        mDownTimer.cancel();
    }

    /**
     * 转换为标准的时间
     * @param timestamp
     * @return
     */
    public String getStandardTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = new Date(timestamp * 1000);
        sdf.format(date);
        return sdf.format(date);
    }

    /**
     * 继承 CountDownTimer 方法
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    class MyCountDownTimer
            extends CountDownTimer
    {
        /**
         *
         * @param millisInFuture
         *      表示以毫秒为单位 倒计时的总数
         *
         *      例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少毫秒 调用一次 onTick 方法
         *
         *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mTv.setText("计时完毕,做你想做的事");

        }
        //分秒位
        private int mm=99;

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            //            mTv.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
            mm--;
            if (mm<=0){
                mm=99;
            }
            mTv.setText("倒计时(" + getStandardTime(millisUntilFinished/1000) +":"+mm+ ")...");
        }
    }
}
