package com.cr.viewbindingdemo

import android.os.Bundle
import android.widget.Toast
import com.cr.viewbindingdemo.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.tvScore.setText("宇宙第一暖男")
        mViewBinding.btnConfirm.setText("请叫暖男")
        mViewBinding.btnConfirm.setOnClickListener {
            Toast.makeText(this, "叫我干啥", Toast.LENGTH_LONG).show()
        }

    }

    override fun getViewBinding(): ActivitySecondBinding {
        return ActivitySecondBinding.inflate(layoutInflater)
    }


}