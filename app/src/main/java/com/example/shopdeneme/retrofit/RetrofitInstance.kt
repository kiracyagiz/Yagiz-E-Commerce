package com.example.shopdeneme.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    const val BASE_URL = "https://kiracyagiz.github.io/yagiz_apideneme/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val AppApi  = retrofit.create<RetrofitService>()
}