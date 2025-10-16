package com.study.android.inner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.study.android.R;

public class TestInnerActivity extends Activity {
    private static final String TAG = "TestInner";

    // 定义一个抽象类
    abstract class MyAbstractClass {
        public abstract void doSomething();
    }


    private TextView testTv;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);

        testTv = findViewById(R.id.testTxt);

        // 使用匿名内部类继承抽象类
        MyAbstractClass myObject = new MyAbstractClass() {
            @Override
            public void doSomething() {
                // 实现抽象方法
            }
        };
        myObject.doSomething();
    }
}
