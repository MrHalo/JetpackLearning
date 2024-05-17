package com.cr.viewbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cr.viewbindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. 初始化activityMainBinding，通过调用inflate方法获取绑定类的实例
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        // 2.通过setContentView方法设置根视图，这样就可以通过绑定类实例操作
        setContentView(activityMainBinding.root)

        activityMainBinding.etInputId.setText("1")
        activityMainBinding.tvScore.setText("分数为90")
        activityMainBinding.btnConfirm.setText("王子请确认")
    }
}