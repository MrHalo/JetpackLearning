package com.cr.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cr.databindingdemo.bean.User
import com.cr.databindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.btnConfirm.setOnClickListener {
            var user = getUser()
//            activityMainBinding.tvUserName.text = user.userName
//            activityMainBinding.tvUserId.text = user.userId
            activityMainBinding.user = user

        }

        activityMainBinding.clickHandlers = ClickHandlers()
    }

    /**
     * 获取用户
     */
    private fun getUser():User{
        return User(getUserName(), getUserId(), "http://gips2.baidu.com/it/u=195724436,3554684702&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960")
    }

    /**
     * 获取输入的用户名
     */
    private fun getUserName(): String? {
        return activityMainBinding.etUserName.text?.toString()
    }

    /**
     * 获取输入的用户id
     */
    private fun getUserId():String?{
        return activityMainBinding.etUserId.text?.toString()
    }

//    fun confirm(view: View) {
//        var user = getUser()
////            activityMainBinding.tvUserName.text = user.userName
////            activityMainBinding.tvUserId.text = user.userId
//        activityMainBinding.user = user
//        Toast.makeText(this, "确定按钮被点击了", Toast.LENGTH_LONG).show()
//    }


}