package com.cr.pagingdemo.bean

class BaseReqData<T> {
    val error_code: Int = 0
    val reason: String? = null
    val result: T? = null
}