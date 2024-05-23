package com.cr.pagingdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceBuilder {
    val baseApi = "http://apis.juhe.cn/"
    fun <T> createService(
        clazz: Class<T>,
        baseApi:String = RetrofitServiceBuilder.baseApi
    ):T?{
        // 添加日志拦截器
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                HttpLoggingInterceptor.Logger.DEFAULT.log(message)
            }
        })

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseApi)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)

    }
}