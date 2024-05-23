package com.cr.pagingdemo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cr.pagingdemo.bean.Newslist
import com.cr.pagingdemo.utils.Constants
import java.io.IOException
import java.lang.Exception

class NewsDataSource: PagingSource<Int, Newslist>() {

    private val newsApi = RetrofitServiceBuilder.createService(NewsApi::class.java)
    override fun getRefreshKey(state: PagingState<Int, Newslist>): Int? {

        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Newslist> {
        try {
            val currentPage = params.key ?: 1
            val loadNews = newsApi?.loadNews(currentPage, params.loadSize, Constants.KEY)

            // 当前页码小于总页码时，页面+1
            val nextPage = if (currentPage < 10 ?: 0){
                currentPage +1
            } else{
                null
            }

            // 上一页
            val prevKey = if (currentPage > 1) {
                currentPage -1
            } else{
                null
            }

            if (loadNews == null) {
                return LoadResult.Error(throwable = IOException())
            }

            when(loadNews.reason) {

                "success" -> {
                    loadNews.result?.newslist?.let {

                        return LoadResult.Page(
                            data = it,
                            prevKey = prevKey,
                            nextKey = nextPage
                        )

                    }
                }

            }


        }catch (e:Exception) {
            return LoadResult.Error(e)
        }
        return LoadResult.Error(throwable = IOException())

    }
}