package com.cr.pagingdemo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cr.pagingdemo.NewsDataSource
import com.cr.pagingdemo.bean.Newslist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


class NewsRepository {

    fun loadNews():Flow<PagingData<Newslist>>{

        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {  NewsDataSource() }
        ).flow
    }
}

/**
 *
 *
 * pagingSourceFactory = {  NewsDataSource() }
 * {}:是Lambda表达式，表示一个匿名函数，这个例子中Lambda表达式没有参数，因此为空括号{}
 * NewsDataSource()：这是Lambda表达式的主体部分，表示匿名函数的实现。它创建了一个NewsDataSource
 * 对象实例，Lambda表达式的最后一个表达式的值将成为Lambda表达式的返回值
 *
 * pagingSourceFactory: () -> PagingSource<Key, Value>
 * 这是函数类型的声明
 *(val1: Int, val2: Int) -> Int
 * 这表示这个函数类型接受两个整数参数，并返回一个整数值
 *
 *
 *
 */