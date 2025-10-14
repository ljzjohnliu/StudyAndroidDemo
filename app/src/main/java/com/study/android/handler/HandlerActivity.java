package com.study.android.handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.study.android.R;

public class HandlerActivity extends Activity {
    private static final String TAG = "HandlerActivity";
    private static final String CURRENT_PROCESS_KEY = "current_process_key";
    private static int mCount = 0;
    private TextView testTv;
    private Button sendMessageBtn;
    private Button postBtn;
    private Button sendToTargetBtn;

    //创建 Handler对象，并关联主线程消息队列
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: currentThread is: " + Thread.currentThread());
            //根据信息编码及数据做出相对应的处理
            switch (msg.what) {
                case 1:
                    //更新 TextView UI
                    testTv.setText("CustomChildThread starting!");
                    break;
                case 2:
                    //获取 ProgressBar 的进度，然后显示进度值
                    Bundle bundle = msg.getData();
                    int process = bundle.getInt(CURRENT_PROCESS_KEY);
//                    Log.d(TAG, "handleMessage: process = " + process);
                    testTv.setText("更新进度：" + process);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 子线程，用于处理耗时工作
     */
    public class CustomChildThread extends Thread {

        @Override
        public void run() {
            //在子线程中创建一个消息对象
            Message childThreadMessage = new Message();
            childThreadMessage.what = 1;
            //将该消息放入主线程的消息队列中
            mHandler.sendMessage(childThreadMessage);

            //模拟耗时进度，将进度值传给主线程用于更新 ProgressBar 进度。
            for (int i = 1; i <= 5; i++) {
                try {
                    //让当前执行的线程（即 CustomChildThread）睡眠 1s
                    Thread.sleep(1000);

                    //Message 传递参数
                    Bundle bundle = new Bundle();
                    bundle.putInt(CURRENT_PROCESS_KEY, i);
//                    Message progressBarProcessMsg = new Message();
                    Message progressBarProcessMsg = Message.obtain();
                    progressBarProcessMsg.setData(bundle);
                    progressBarProcessMsg.what = 2;
                    Log.d(TAG, "run: progressBarProcessMsg = " + progressBarProcessMsg);
                    mHandler.sendMessage(progressBarProcessMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        testTv = findViewById(R.id.testTxt);
        mCount++;
        testTv.setText("该Activity创建了" + mCount + "次");
        sendMessageBtn = findViewById(R.id.sendMessage);
        sendMessageBtn.setOnClickListener(v -> {
            //开启子线程，子线程处理UI工作
            CustomChildThread customThread = new CustomChildThread();
            customThread.start();
        });
        postBtn = findViewById(R.id.post);
        postBtn.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    Log.d(TAG, "postDelayed: currentThread is: " + Thread.currentThread());
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "postBtn run: currentThread is: " + Thread.currentThread());
                        }
                    }, 2000);
                }
            }.start();
        });
        sendToTargetBtn = findViewById(R.id.sendToTarget);
        sendToTargetBtn.setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    Log.d(TAG, "sendToTarget: currentThread is: " + Thread.currentThread());

//                    Message message = Message.obtain();
//                    message.setTarget(mHandler); //手动调用 message.setTarget(handler)
                    Message message = mHandler.obtainMessage(); //使用 Handler.obtainMessage() 创建 Message
                    message.what = 1;
                    message.sendToTarget();
                }
            }.start();
        });
    }
}
