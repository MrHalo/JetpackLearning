package com.cr.databindingdemo

import android.util.Log
import android.view.View
import com.cr.databindingdemo.bean.User

class ClickHandlers {
    var TAG = "ClickHandlers"

    fun confirm1(view : View){
        Log.d(TAG, "确认1按钮被点击了")
    }
    fun confirm2(view: View, user: User?) {
        Log.d(TAG, "确认2按钮被点击了")
    }
}