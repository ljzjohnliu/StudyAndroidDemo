package com.study.android.refreshui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.study.android.R;

public class RefreshUIActivity extends Activity {
    private static final String TAG = "RefreshUIActivity";
    private TextView testTv;
    private Button refreshUIBtn;
    private Button refreshUIBtn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_ui);

        testTv = findViewById(R.id.testTxt);
        refreshUIBtn = findViewById(R.id.refreshUIBtn);
        refreshUIBtn2 = findViewById(R.id.refreshUIBtn2);
        refreshUI2();
        refreshUIBtn.setOnClickListener(v -> {
            try {
                refreshUI();
            } catch (Throwable t) {
                Log.d(TAG, "onCreate: t = " + t);
            }
//            refreshUI();
        });
        refreshUIBtn2.setOnClickListener(v -> {
            refreshUI2();
        });
    }

    public void refreshUI(){
        HandlerThread newThread = new HandlerThread("NewThread");
        newThread.start();
        Handler newThreadHandler = new Handler(newThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG, "refreshUI, handleMessage: currentThread : " + Thread.currentThread());
                testTv.setText("子线程内更新 UI");
                ViewGroup.LayoutParams params = testTv.getLayoutParams();
                testTv.setLayoutParams(params);
            }
        };
        newThreadHandler.sendEmptyMessage(0);
    }

    public void refreshUI2(){
        new Thread(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void run() {
                Log.d(TAG, "refreshUI2, run: currentThread : " + Thread.currentThread());
                testTv.setText("子线程内更新 UI 222");
                testTv.setTextColor(R.color.purple_500);
            }
        }.start();
    }
}
