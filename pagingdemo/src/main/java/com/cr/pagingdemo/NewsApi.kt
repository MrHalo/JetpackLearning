package com.cr.pagingdemo

import com.cr.pagingdemo.bean.BaseReqData
import com.cr.pagingdemo.bean.Result
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("fapigx/internet_news/query")
    suspend fun loadNews(
        @Query("num") num: Int,
        @Query("page") page: Int,
        @Query("key") key: String
    ): BaseReqData<Result>
}