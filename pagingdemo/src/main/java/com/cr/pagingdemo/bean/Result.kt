package com.cr.pagingdemo.bean

data class Result(
    val allnum: Int,
    val curpage: Int,
    val newslist: List<Newslist>
)