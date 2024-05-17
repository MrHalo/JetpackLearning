package com.cr.viewmodeldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class AdvertisingActivity : AppCompatActivity() {
    val TAG: String = "AdvertisingActivity";

    lateinit var btnIgnore: Button
    lateinit var tvAdvertising: TextView
    private var adertisingManager2: AdertisingManager2? = null
    private lateinit var advertisingViewModel: AdvertisingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        btnIgnore = findViewById(R.id.btn_ignore)
        tvAdvertising = findViewById(R.id.tv_advertising_time)

        //
        advertisingViewModel = ViewModelProvider(this).get(AdvertisingViewModel::class.java)
        adertisingManager2 = AdertisingManager2(advertisingViewModel.millisInFuture)

        Log.d(TAG, "advertisingViewModel.millisInFuture:  ${advertisingViewModel.millisInFuture}")
        lifecycle.addObserver(adertisingManager2!!)



        adertisingManager2?.advertisingManagerListener2 =
            object : AdertisingManager2.AdvertisingManagerListener2 {
                override fun timing(second: Int) {

                    // 第三章：使用ViewModel
//                    tvAdvertising.text = "广告剩余$second 秒"
//                    advertisingViewModel?.millisInFuture = (second*1000).toLong()
                    // 第四章：使用LiveData
                     advertisingViewModel?.setTimingResult(second)


                }

                override fun enterMainActivity() {

                    val intent = Intent(this@AdvertisingActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

        /**
         * 在Kotlin中，it是一个隐式参数，通常用于Lambda表达式或单参数的匿名函数中。它表示Lambda表达式中的单个参数，
         * 特别是在Lambda表达式中只有一个参数时，可以使用it来代替参数名，从而简化代码。
         */
        advertisingViewModel?._timingResult?.observe(this, Observer {

            tvAdvertising.text = "广告剩余$it 秒"
            if (it == 0) {
                Log.i(TAG, "广告结束，准备进入主页面")
            }

        })
        btnIgnore.setOnClickListener {

            val intent = Intent(this@AdvertisingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}