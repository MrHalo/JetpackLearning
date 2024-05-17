package com.cr.databindingdemo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ItemBind{
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(ivIcon: ImageView, imageUrl: String?) {
        Glide.with(ivIcon.context).load(imageUrl).into(ivIcon)
    }
}

