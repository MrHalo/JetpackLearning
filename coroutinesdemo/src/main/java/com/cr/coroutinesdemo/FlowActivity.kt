package com.cr.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.cr.coroutinesdemo.databinding.ActivityFlowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {
    val TAG = "FlowActivity"
    lateinit var activityFlowBinding: ActivityFlowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFlowBinding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(activityFlowBinding.root)

        lifecycleScope.launch{
            // 需要使用collect方法对Flow进行订阅
//            loadData().collect(){
//                Log.d(TAG, it.toString())
//            }

            loadData1().collect(){
                Log.d(TAG, it.toString())
            }
        }

    }

    private fun loadData() = flow<Int> {
        Log.d(TAG, "__进入loadData方法__")
        for (i in 1..5) {
            kotlinx.coroutines.delay(1000)
            emit(i)
        }
    }.filter {
        it % 2 == 0
    } .map {
        it * 5
    }

    /**
     * 将Flow代码块中的操作放在了I/O线程中
     * 借助flowOn
     */
    private fun loadData1() = flow<Int> {
        Log.d(TAG, "__进入loadData1方法__")
        for (i in 1..5) {
            kotlinx.coroutines.delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)
}