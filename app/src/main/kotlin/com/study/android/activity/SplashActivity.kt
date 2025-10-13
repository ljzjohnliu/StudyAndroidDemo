package com.study.android.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.study.android.R
import com.study.android.launchmode.SingleInstanceActivity
import com.study.android.launchmode.SingleTaskActivity
import com.study.android.launchmode.SingleTopActivity
import com.study.android.launchmode.StandardActivity
import com.study.android.refreshui.RefreshUIActivity
import com.study.android.utils.ViewUtil


@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    private val REQUEST_READ_STORAGE = 1

    private var launchmodeBtn: Button? = null
    private var uiBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
        requestStoragePermission()

        Log.d("debug_sr", "onCreate: 300dp is : " + ViewUtil.dp2px(this, 300f) + ", 400dp is : " + ViewUtil.dp2px(this, 400f))
//        Log.d("TAG", "onCreate: " + LogUtils.readFile("/sdcard/frescolog/image.json"))
    }

    private fun initViews() {
        launchmodeBtn = findViewById(R.id.launchmodeTest)
        uiBtn = findViewById(R.id.uiTest)

        launchmodeBtn!!.setOnClickListener {
//            startActivity(Intent(this, StandardActivity::class.java))
//            startActivity(Intent(this, SingleTopActivity::class.java))
//            startActivity(Intent(this, SingleTaskActivity::class.java))
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }
        uiBtn!!.setOnClickListener{
            startActivity(Intent(this, RefreshUIActivity::class.java))
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_STORAGE
        )
    }
}
