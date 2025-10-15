package com.study.android.sharedpreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.study.android.R;
import com.tencent.mmkv.MMKV;

public class SharedPreferencesActivity extends Activity {
    private static final String TAG = "SharedPreferences";
    private static int mCount = 0;
    private Context context;
    private static final String SP_NAME = "test_sp";
    private static final String SP_KEY_NAME = "sp_key";
    private static final String MMKV_KEY_NAME = "mmkv_key";
    private SharedPreferences sharedPreferences;
    //1. 获取默认全局实例 (与下面的几选一，一般就使用这个就行)
    MMKV mmkv = MMKV.defaultMMKV();
    //2. 也可以自定义MMKV对象，设置自定ID  (根据业务区分的存取实例)
//    MMKV mmkv = MMKV.mmkvWithID("ID");
    //3. MMKV默认是支持单进程的，如果业务需要多进程访问，需要在初始化的时候添加多进程模式参数
//    MMKV mmkv = MMKV.mmkvWithID("ID", MMKV.MULTI_PROCESS_MODE);  //多进程同步支持

    private TextView testTv;
    private Button writeSpBtn;
    private Button readSpBtn;
    private Button writeMMKVBtn;
    private Button readMMKVBtn;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        context = this;
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        testTv = findViewById(R.id.testTxt);
        mCount++;
        testTv.setText("该Activity创建了" + mCount + "次");
        writeSpBtn = findViewById(R.id.writeSp);
        writeSpBtn.setOnClickListener(v -> {
            writeSp();
        });
        readSpBtn = findViewById(R.id.readSp);
        readSpBtn.setOnClickListener(v -> {
            readSp();
        });
        writeMMKVBtn = findViewById(R.id.writeMMKV);
        writeMMKVBtn.setOnClickListener(v -> {
            writeMMKV();
        });
        readMMKVBtn = findViewById(R.id.readMMKV);
        readMMKVBtn.setOnClickListener(v -> {
            readMMKV();
        });
    }

    private void writeSp() {
        String jsonString = "{\"name\":\"李四\",\"age\":25}";
        Log.d(TAG, "writeSp: jsonString = " + jsonString);
        Log.d(TAG, "writeSp: gson jsonString = " + new Gson().toJson(jsonString));
        sharedPreferences.edit().putString(SP_KEY_NAME, new Gson().toJson(jsonString)).commit();//这里推荐使用apply替换commit
    }

    private void readSp() {
        String jsonString = sharedPreferences.getString(SP_KEY_NAME, "");
        Log.d(TAG, "readSp: get value is: " + jsonString);
    }

    private void writeMMKV() {
        String jsonString = "{\"name\":\"李四\",\"age\":25}";
        Log.d(TAG, "writeMMKV: jsonString = " + jsonString);
        Log.d(TAG, "writeMMKV: gson jsonString = " + new Gson().toJson(jsonString));
        mmkv.encode(MMKV_KEY_NAME, new Gson().toJson(jsonString));
    }

    private void readMMKV() {
        String jsonString = mmkv.getString(MMKV_KEY_NAME, "");
        Log.d(TAG, "readMMKV: get value is: " + jsonString);
    }
}
