package com.meng.eventbusdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    private TextView tv2;
    private Button btn2;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_second);

        tv2 = (TextView) findViewById(R.id.tv);
        tv2.setText(text);

    }
    //使用粘性订阅事件
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getStatus(Status status){
        Logger.e(status.text);
        this.text = status.text;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
