package com.cr.viewmodeldemo.map

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cr.viewmodeldemo.R

class StudentActivity : AppCompatActivity() {
    private lateinit var tvScore:TextView
    private lateinit var studentViewModel: StudentViewModel

    private lateinit var btnConfirm:Button
    private lateinit var etInput:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        tvScore = findViewById(R.id.tv_score)
        btnConfirm = findViewById(R.id.btn_confirm)
        etInput = findViewById(R.id.et_input_id)
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)


        studentViewModel.newScore.observe(this, Observer {
            tvScore.text = "分数：$it"
        })
        btnConfirm.setOnClickListener {
             studentViewModel.setStudentId(etInput.text.toString().trim())
        }



//        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
//        val student = Student("liming", "123", 80)
//        studentViewModel.setStudentMessage(student)
////        studentViewModel._student.observe(this, Observer {
////            tvScore.text = it.score.toString()
////        })
//
//        studentViewModel._studentmap.observe(this, Observer {
//            tvScore.text = it.toString()
//        })
    }
}