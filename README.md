# StudyAndroidDemo

## 简介

调试日志
logcat -s StandardActivity -s SingleTopActivity -s SingleTaskActivity -s SingleInstanceActivity -s RefreshUIActivity -s HandlerActivity

### 启动模式
#### 标准启动模式
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

#### SingleTop singleTask singleInstance
该启动模式，在当前界面再次startActivity的话不会创建新的activity实例，但是当前的实例会先onPause再onResume
10-13 16:18:34.159 24920 24920 D SingleTopActivity: onCreate: com.study.android.launchmode.SingleTopActivity@1617acd
10-13 16:18:34.160 24920 24920 D SingleTopActivity: onStart: com.study.android.launchmode.SingleTopActivity@1617acd
10-13 16:18:34.162 24920 24920 D SingleTopActivity: onResume: com.study.android.launchmode.SingleTopActivity@1617acd
10-13 16:18:34.162 24920 24920 D SingleTopActivity: onPostResume: com.study.android.launchmode.SingleTopActivity@1617acd

10-13 16:18:37.470 24920 24920 D SingleTopActivity: onPause: com.study.android.launchmode.SingleTopActivity@1617acd
10-13 16:18:37.472 24920 24920 D SingleTopActivity: onResume: com.study.android.launchmode.SingleTopActivity@1617acd
10-13 16:18:37.472 24920 24920 D SingleTopActivity: onPostResume: com.study.android.launchmode.SingleTopActivity@1617acd

singleTop 是栈顶复用模式。在这种模式下，如果新启动的 Activity 已经在任务栈的栈顶了，那么就不会重新创建新的实例，而是调用这个 Activity 的 onPause、onNewIntent 以及 onResume 方法。如果新启动的 Activity 不是位于栈顶，
那么还是会重新创建。
比如现在栈内情况是 ABCD 四个Activity，A 位于栈底，D 位于栈顶。如果 D 的启动模式为 singleTop，那么不会再次创建 D 的实例，栈内依然是 ABCD。
如果上面的 D 为 standard 启动模式，那么栈内将变为 ABCDD。

singleTask 是栈内复用模式。这是最复杂的一种模式，因为它可能涉及到多个栈。当一个具有 singleTask 模式的 Activity 启动后，比如 Activity A，系统会首先寻找是否存在所需的任务栈，如果不存在，就重新创建一个任务栈，
然后创建 A 的实例后把 A 放入到栈中。如果存在 A 所需要的任务栈，这时要看 A 是否在栈中有实例存在，如果有，那么系统就会把它调到栈顶并且调用它的 onNewIntent 方法，如果不存在，就创建 A 的实例并把 A 压入栈中。
这里所说的 A 所需要的任务栈是什么意思呢?其实 Activity 是可以指定自己想要的任务栈的名字的，通过一个参数：TaskAffinity，默认情况下，所有的 Activity 所需要的任务栈的名字为应用的包名。
如果任务栈 S1 中的情况为 ABC，这个时候 Activity D 以 singleTask 模式请求启动，它需要的任务栈为 S2，由于 S2 和 D 的实例均不存在，所以系统就会先创建任务栈 S2，然后在创建 D 的实例并将其入栈到 S2
如果上面 D 所需的任务栈为 S1,那么因为 S1 已经存在，所以系统直接创建 D 的实例并且入栈到 S1。
如果 D 所需的任务栈为 S1，但是 S1 中的情况为 ADBC，此时 D 不会重新创建，而是把 D 切换到栈顶并调用 onNewIntent 方法。那 B 和 C 怎么办?它们会全部出栈，**相当于 clearTop 效果**。

singleInstance 是单实例模式。这种模式是 singleTask 的加强版，它除了具有 singleTask 的所有特性外，还加强了一点，那就是此种模式的 Activity 只能单独位于一个任务栈中。
比如 Activity A 是 singleInstance 模式，当 A 启动后，系统会创建一个新的任务栈，然后 A 独自在这个新的任务栈中，由于栈内复用的特性，后续的请求均不会创建新的 Activity，除非这个栈被销毁了;

singleInstance表示全局只有一个activity实例。这个Activity得到一个唯一的Task，只有它自己在运行；如果它再次以相同的Intent启动，那么该Task将会被移动到前台，并且它的Activity.onNewIntent()方法被调用。如果这个Activity尝试启动
一个新Activity，这个新活动将在一个单独的任务栈中启动。而singleInstancePerTask作用和singleTask相当，只不过会为启动的Activity新建任务栈，同时配合Intent.FLAG_ACTIVITY_MULTIPLE_TASK或Intent.FLAG_ACTIVITY_NEW_DOCUMENT，
singleInstancePerTask可以同时存在Activity在不同任务栈中。


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