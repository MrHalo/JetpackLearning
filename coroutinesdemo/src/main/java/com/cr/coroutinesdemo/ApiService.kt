package com.cr.coroutinesdemo

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun queryData():Call<List<UserBean>>

    @GET("users")
    suspend fun queryDataByCoroutine():List<UserBean>
}