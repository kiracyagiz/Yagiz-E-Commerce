package com.example.shopdeneme.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setPicture(url: String?) {

    if (url != null){
        Glide.with(this).load(url).into(this)
    }
    else{

    }

}