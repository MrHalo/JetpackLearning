package com.cr.kotlindemo1

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AdvertisingManager : LifecycleObserver {
    var TAG = "AdvertisingManager"

    var advertisingManagerListener: AdvertisingManagerListener? = null

    // 定时器
    private var countDownTimer: CountDownTimer? = object : CountDownTimer(5000, 1000){
        override fun onTick(millisUntilFinished: Long) {

            Log.d(TAG, "广告剩余：${(millisUntilFinished/1000).toInt()}秒")
            advertisingManagerListener?.timing((millisUntilFinished/1000).toInt())
        }

        override fun onFinish() {
            advertisingManagerListener?.enterMainActivity()
        }

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start(){
        Log.d(TAG, "开始计时")
        countDownTimer?.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onCancel() {
        Log.d(TAG, "结束计时")
        countDownTimer?.cancel()
        countDownTimer = null
    }


    interface AdvertisingManagerListener{
        fun timing(second: Int)
        fun enterMainActivity()
    }
}