# StudyAndroidDemo

## 简介

学习Okhttp使用

调试日志
logcat -s RefreshUIActivity


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