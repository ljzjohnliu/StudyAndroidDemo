package com.study.android.customview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.study.android.R;

public class CustomViewActivity extends Activity {
    private static final String TAG = "SharedPreferences";
    private static int mCount = 0;

    private TextView testTv;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        testTv = findViewById(R.id.testTxt);
        mCount++;
        testTv.setText("该Activity创建了" + mCount + "次");
    }
}
