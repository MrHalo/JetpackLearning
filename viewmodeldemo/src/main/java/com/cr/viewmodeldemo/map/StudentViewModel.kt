package com.cr.viewmodeldemo.map

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class StudentViewModel: ViewModel() {

    private var studentLiveData = MutableLiveData<Student>()

    // 提供给外部使用，保证数据完整性
    val _student: LiveData<Student>
        get() = studentLiveData


    val _studentmap: LiveData<Int>
        get() = Transformations.map(studentLiveData){
            it.score
        }
    /**
     * 设置学生信息
     */

    fun setStudentMessage(student: Student){
        studentLiveData.value = student
    }

    /**
     * 获取分数
     * @param id 学生id
     */
    fun getScore(id:String):LiveData<Int> {
        return StudentRespository().getStudentScore(id)
    }

    private var studentIdLiveData = MutableLiveData<String>()

    fun setStudentId(studentId: String){
        studentIdLiveData.value = studentId
    }

    var newScore:LiveData<Int> = Transformations.switchMap(studentIdLiveData,
        object : Function<String, LiveData<Int>>{
            override fun apply(input: String): LiveData<Int> {
                return StudentRespository().getStudentScore(input)
            }
        })
}