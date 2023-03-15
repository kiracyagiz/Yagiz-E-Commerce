package com.example.shopdeneme.retrofit

import com.example.shopdeneme.data.Products
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("yagiz_api.json")
     fun getAllData() : Call<List<Products>>



}