package com.cr.kotlindemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    lateinit var tipDialog: TipDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tipDialog = TipDialog(this)
        tipDialog?.show()
        Handler().postDelayed(
            {
                finish()
            }, 2 * 1000)
    }

    // 不适用Lifecycle
//    override fun onDestroy() {
//        super.onDestroy()
//        tipDialog?.dismiss()
//    }
}