package com.cr.viewmodeldemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AdvertisingViewModel(application: Application) : AndroidViewModel(application) {

    var millisInFuture:Long = 10000
    /**
     * 计时结果
     */

    private var timingResult = MutableLiveData<Int>()

    /**
     * 将timingreuslt变量私有，避免外部可以直接赋值
     * 对于这种情况一般会单独声明非私有类型LiveData类型的变量，并给这个变量赋值为timingResult
     *
     */
    val _timingResult: LiveData<Int>
        get() = timingResult

    fun setTimingResult(millisInFuture: Int){
        timingResult.value = millisInFuture
    }
}