package com.cr.viewmodeldemo.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StudentRespository {

    /**
     * 根据id获取分数，模拟网络请求
     */

    fun getStudentScore(id: String): LiveData<Int> {
        val studentLiveData = MutableLiveData<Int>()
        if (id == "1") {
            studentLiveData.value = 90
        } else{
            studentLiveData.value = 60
        }
        return studentLiveData
    }
}
