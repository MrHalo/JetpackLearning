package com.cr.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.cr.coroutinesdemo.databinding.ActivityRetrofitBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitActivity : AppCompatActivity() {
    val TAG = "RetrofitActivity"
    lateinit var activityRetrofitBinding: ActivityRetrofitBinding
    lateinit var job:Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRetrofitBinding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(activityRetrofitBinding.root)


        job = Job()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // 1. 传统方式做retrofit请求
//        requestData(apiService)
        // 2. 使用协程做请求
         requestDataByCoroutine(apiService)



        lifecycleScope.launch {
            Log.d(TAG, "lifecycleScope.launch")
        }
    }


    private fun requestDataByCoroutine(apiService: ApiService) {
        CoroutineScope(job).launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                apiService.queryDataByCoroutine()
            }
            activityRetrofitBinding.tvDataShow.text = Gson().toJson(result)
        }


    }

    private fun requestData(apiService:ApiService){
        apiService.queryData()
            .enqueue(object : Callback<List<UserBean>>{
                override fun onResponse(
                    call: Call<List<UserBean>>,
                    response: Response<List<UserBean>>
                ) {
                    activityRetrofitBinding.tvDataShow.text = Gson().toJson(response.body())

                }

                override fun onFailure(call: Call<List<UserBean>>, t: Throwable) {
                    Log.d(TAG, "请求失败")
                }

            })
    }


}