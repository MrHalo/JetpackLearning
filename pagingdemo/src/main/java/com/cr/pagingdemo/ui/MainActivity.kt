package com.cr.pagingdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cr.pagingdemo.NewsApi
import com.cr.pagingdemo.RetrofitServiceBuilder
import com.cr.pagingdemo.databinding.ActivityMainBinding
import com.cr.pagingdemo.ui.adapter.NewsDataAdapter
import com.cr.pagingdemo.utils.Constants
import com.cr.pagingdemo.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity_CJ"

    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        recyclerView = activityMainBinding.rvRecyclerview
        val newsDataAdapter = NewsDataAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsDataAdapter


        lifecycleScope.launch{
            newsViewModel.loadNews().collect(){
                Log.d(TAG, "newsViewModel.loadNews().collect()" )
                newsDataAdapter.submitData(it)

            }
        }

        newsDataAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> {
                    Log.d(TAG, "加载错误")
                }
                LoadState.Loading -> {

                    Log.d(TAG, "加载进行中")
                }
                is LoadState.NotLoading -> {

                    Log.d(TAG, "未加载，无错误")
                }
            }
        }



    }

    private fun retrofit() {
        val newsApi = RetrofitServiceBuilder.createService(NewsApi::class.java)
        lifecycleScope.launch {

            val loadNews = newsApi?.loadNews(10, 1, Constants.KEY)
        }
    }

    private fun originalCreateApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var newsApi = retrofit.create<NewsApi>()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                var result = newsApi.loadNews(1, 1, "ac38c9a64b8d4accd8df077912ba87f7")

                Log.d(TAG, result.toString())
            }
        }
    }
}