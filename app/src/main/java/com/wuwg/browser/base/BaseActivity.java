package com.wuwg.browser.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wuwengao on 2018/1/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = obtainLayoutId();
        setContentView(layoutId);
        initView();
        initData();
        inirListener();
    }


    protected abstract int obtainLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void inirListener();
}
