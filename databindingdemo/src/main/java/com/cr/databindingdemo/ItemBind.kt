package com.cr.databindingdemo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ItemBind {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(ivIcon: ImageView, imageUrl: String?) {
        Glide.with(ivIcon.context).load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(ivIcon)
    }

    @BindingAdapter(
        value = ["android:imageUrl", "android:gender"],
        requireAll = false
    )
    @JvmStatic
    fun setIcon(ivIcon: ImageView, imageUrl: String?, gender: Int) {
        if (gender == 1) {
            Glide.with(ivIcon.context).load(imageUrl).placeholder(R.drawable.user_icon).into(ivIcon)
        } else {
            Glide.with(ivIcon.context).load(imageUrl).placeholder(R.drawable.user_icon_2).into(ivIcon)
        }
    }
}

