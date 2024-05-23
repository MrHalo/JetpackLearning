package com.cr.pagingdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cr.pagingdemo.bean.Newslist
import com.cr.pagingdemo.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel(application: Application):AndroidViewModel(application) {


    fun loadNews():Flow<PagingData<Newslist>> {
        return NewsRepository().loadNews().cachedIn(viewModelScope)
    }
}