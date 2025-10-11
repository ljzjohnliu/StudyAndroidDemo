package com.study.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.study.android.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkhttpActivity extends Activity {
    private static final String TAG = "TestOkhttpActivity";
    private String testUrl = "http://www.baidu.com";
    private String testUrl2 = "https://wanandroid.com/wxarticle/list/408/1/json";
    private Button syncBtn;
    private Button asyncBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp);

        syncBtn = findViewById(R.id.syncTest);
        asyncBtn = findViewById(R.id.asyncTest);

        syncBtn.setOnClickListener(v -> {
            getDataSync();
        });
        asyncBtn.setOnClickListener(v -> {
            getDataAsync();
        });
    }

    public void getDataSync(){
        new Thread(() -> {
            try {
                Log.d(TAG, "run: -------getDataSync start--------");
                //创建OkHttpClient对象
                OkHttpClient client = new OkHttpClient();
                //创建Request 对象
                Request request = new Request.Builder()
                        .url(testUrl2)
                        .build();
                Response response = client.newCall(request).execute();//得到Response 对象
                Log.d(TAG, "getDataSync, run: -------response = " + response);
                if (response.isSuccessful()) {
                    Log.d(TAG,"getDataSync, response.code() = " + response.code() + ", message = " + response.message());
                    if (response.body() != null) {
                        Log.d(TAG,"getDataSync, response.body is:" + response.body().string());
                    }
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "getDataSync, run: e = " + e);
            }
        }).start();
    }

    public void getDataAsync(){
        new Thread(() -> {
            try {
                Log.d(TAG, "run: -------getDataAsync start--------");
                //创建OkHttpClient对象
                OkHttpClient client = new OkHttpClient();
                //创建Request 对象
                Request request = new Request.Builder()
                        .url(testUrl2)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d(TAG, "getDataAsync, onFailure: IOException = " + e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d(TAG, "getDataAsync, onResponse: code = " + response.code() + ", message = " + response.message());
                        if (response.body() != null) {
                            Log.d(TAG,"getDataAsync, response.body is:" + response.body().string());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "getDataAsync, run: e = " + e);
            }
        }).start();
    }
}
