package com.study.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.youth.banner.Banner;

public class MyBanner extends Banner {
    private static final String TAG = "MyBanner";

    public MyBanner(Context context) {
        super(context);
        init(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Log.d(TAG, "MyBanner: init----");
        // 初始化代码
        if (attrs != null) {
//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyBanner);
//            // 读取自定义属性
//            a.recycle();
        }
    }
}
