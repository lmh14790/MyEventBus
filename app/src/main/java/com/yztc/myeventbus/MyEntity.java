package com.yztc.myeventbus;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MyEntity {
    public MyEntity(int count) {
        this.count = count;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;
}
