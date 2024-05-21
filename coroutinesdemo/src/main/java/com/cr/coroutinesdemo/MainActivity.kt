package com.cr.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cr.coroutinesdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity_CJ"
    lateinit var job:Job
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        GlobalScope.launch {


            Log.d("当前线程：", Thread.currentThread().name)
        }

        job = Job()
        CoroutineScope(job).launch {
            val startTime = System.currentTimeMillis()
            val result = async {

                delay(2 * 1000)
                "操作成功"
            }

            val result2 = async {
                delay(3000)
                "获取成功"
            }
            Log.d(TAG, "执行结果：${result.await()}")
            Log.d(TAG, "执行结果：${result2.await()}")


            val endTime = System.currentTimeMillis()
            Log.d(TAG, "执行时间：" + (endTime - startTime))
            Log.d(TAG, "当前线程：" + Thread.currentThread().name)


        }

        // withcontext使用

        CoroutineScope(job).launch {
            Log.d(TAG, "withContext start")
            val result = withContext(Dispatchers.IO) {
                delay(2000)
                "withContext 操作成功"
            }

            Log.d(TAG, result)

        }

        loadDataFromNetwork()

    }

    private fun loadDataFromNetwork() {
        CoroutineScope(job).launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                delay(2000)
                "loadDataFromNetwork 操作成功"
            }
            activityMainBinding.tvShow.text = result

            val result1 = withContext(Dispatchers.IO) {
                delay(2000)
                "loadDataFromNetwork 操作成功, yeyeye"
            }
            activityMainBinding.tvShow.text = result1
        }
    }
}