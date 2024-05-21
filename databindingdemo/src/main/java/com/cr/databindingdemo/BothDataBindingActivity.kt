package com.cr.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cr.databindingdemo.bean.User1
import com.cr.databindingdemo.databinding.ActivityBothDataBindingBinding

class BothDataBindingActivity : AppCompatActivity() {
    lateinit var activityBothDataBindingBinding: ActivityBothDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBothDataBindingBinding = ActivityBothDataBindingBinding.inflate(layoutInflater)
        setContentView(activityBothDataBindingBinding.root)

        activityBothDataBindingBinding.user1 = User1()


    }
}