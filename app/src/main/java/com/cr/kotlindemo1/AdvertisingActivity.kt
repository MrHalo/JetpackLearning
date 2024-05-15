package com.cr.kotlindemo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * AppCompatActivity是构造函数，如果是继承的话。如果是实现的话，无需考虑参数
 */
class AdvertisingActivity : Activity(), LifecycleOwner {
    // lateinit 延时初始化
    lateinit var btnIgnore: Button
    lateinit var tvAdvertisingTv: TextView
    private var advertisingManager: AdvertisingManager? = null
    lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)

        btnIgnore = findViewById(R.id.btn_ignore);
        tvAdvertisingTv = findViewById(R.id.tv_advertising_time);
        advertisingManager = AdvertisingManager()
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycle.addObserver(advertisingManager!!)
        advertisingManager?.advertisingManagerListener = object : AdvertisingManager.AdvertisingManagerListener{
            override fun timing(second: Int) {
                tvAdvertisingTv.text = "广告剩余${second} 秒"
            }

            override fun enterMainActivity() {
                val intent = Intent(this@AdvertisingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        btnIgnore.setOnClickListener{
            val intent = Intent(this@AdvertisingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


//        advertisingManager?.start()


    }

    override fun onDestroy() {
        super.onDestroy()
//        advertisingManager?.onCancel()
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}