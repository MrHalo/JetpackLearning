package com.cr.pagingdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cr.pagingdemo.bean.Newslist
import com.cr.pagingdemo.databinding.ItemNewsBinding

class NewsDataAdapter: PagingDataAdapter<Newslist, RecyclerView.ViewHolder>(object :DiffUtil.ItemCallback<Newslist>(){
    override fun areItemsTheSame(oldItem: Newslist, newItem: Newslist): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Newslist, newItem: Newslist): Boolean {
        return oldItem == newItem
    }


}) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val myHolder = holder as NewsDataViewHolder
        val bean : Newslist? = getItem(position)
        bean?.let {
            myHolder.dataBindingUtil.news = bean
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding:ItemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsDataViewHolder(binding)

    }

    class NewsDataViewHolder(var dataBindingUtil:ItemNewsBinding):RecyclerView.ViewHolder(dataBindingUtil.root){


    }
}