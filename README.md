# StudyAndroidDemo

## 简介

调试日志
logcat -s StandardActivity -s RefreshUIActivity

### 启动模式
StandardActivity的启动模式是标准的，在当前界面再次startActivity的话会启动一个新的activity
压入栈，前一个Activity实例先执行onPause，先失去交互能力，再执行新的Activity的onCreate、onStart、onResume，新的activity获取交互能力，这时候前一个不可见了再执行其onStop方法
10-13 16:09:26.462 23775 23775 D StandardActivity: onCreate: com.study.android.launchmode.StandardActivity@c2e58b8
10-13 16:09:26.463 23775 23775 D StandardActivity: onStart: com.study.android.launchmode.StandardActivity@c2e58b8
10-13 16:09:26.465 23775 23775 D StandardActivity: onResume: com.study.android.launchmode.StandardActivity@c2e58b8
10-13 16:09:26.465 23775 23775 D StandardActivity: onPostResume: com.study.android.launchmode.StandardActivity@c2e58b8

10-13 16:09:29.104 23775 23775 D StandardActivity: onPause: com.study.android.launchmode.StandardActivity@c2e58b8
10-13 16:09:29.120 23775 23775 D StandardActivity: onCreate: com.study.android.launchmode.StandardActivity@b248da9
10-13 16:09:29.121 23775 23775 D StandardActivity: onStart: com.study.android.launchmode.StandardActivity@b248da9
10-13 16:09:29.122 23775 23775 D StandardActivity: onResume: com.study.android.launchmode.StandardActivity@b248da9
10-13 16:09:29.123 23775 23775 D StandardActivity: onPostResume: com.study.android.launchmode.StandardActivity@b248da9
10-13 16:09:29.685 23775 23775 D StandardActivity: onStop: com.study.android.launchmode.StandardActivity@c2e58b8


### 只更新Text不会崩溃
public void refreshUI(){
    HandlerThread newThread = new HandlerThread("NewThread");
    newThread.start();
    Handler newThreadHandler = new Handler(newThread.getLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "refreshUI, handleMessage: currentThread : " + Thread.currentThread());
            testTv.setText("子线程内更新 UI");
            //ViewGroup.LayoutParams params = testTv.getLayoutParams();
            //testTv.setLayoutParams(params);
        }
    };
    newThreadHandler.sendEmptyMessage(0);
}

把注释的setLayoutParams放开后，在运行就会报错
android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
这个异常是无法try-catch的！