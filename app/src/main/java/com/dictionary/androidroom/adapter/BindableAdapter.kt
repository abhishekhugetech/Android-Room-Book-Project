package com.dictionary.androidroom.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object BindableAdapter{

    @BindingAdapter("app:setImage")
    @JvmStatic
    fun setImage(view: ImageView, imageUrl:String){
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }

}