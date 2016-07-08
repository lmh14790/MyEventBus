package com.yztc.myeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.sendEvent)
    Button sendEvent;
    @InjectView(R.id.show)
    TextView show;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
    }
    //默认的为当前post出去的线程，ThreadMode.MAIN ui线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiverMsg(MyEntity entity) {
         show.setText(entity.getCount()+"");
    }

    @OnClick(R.id.sendEvent)
    public void onClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyEntity entity = new MyEntity(count);
                while (true){
                    entity.setCount(count);
                    EventBus.getDefault().post(entity);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }

            }
        }).start();
    }
}
