package com.example.shopdeneme.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.retrofit.RetrofitInstance

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ProductsRepository(context : Context) {

    private val productsService = RetrofitInstance.AppApi

 //   private val dataDaoInterface :MyDao? = MyDataBase.roomData(context)?.myDao()


    var top10productsList = MutableLiveData<List<Products>>()
    var productsList = MutableLiveData<List<Products>>()

    @OptIn(DelicateCoroutinesApi::class)
    fun products() {
        GlobalScope.launch {
            try {
                val response = productsService.getAllData().awaitResponse()
                if (response.isSuccessful) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val unsortedProducts = response.body()
                            val sortedProducts = unsortedProducts?.sortedByDescending { it.stock }
                            top10productsList.postValue(sortedProducts?.take(10))
                            productsList.postValue(response.body())
                            //dataDaoInterface?.getAllData()
                            Log.i("RESPONSE_BODY", responseBody.toString())
                        } else {
                            //dataDaoInterface?.insertData(response.body())
                            Log.d("PRODUCTS_ERROR","ERROR")

                        }
                    }


                }
            } catch (e: Exception) {
                Log.e("PRODUCTS_ERROR", e.message.toString())
            }
        }
    }


}

   /*      productsService.getAllData().enqueue(object : Callback<List<Products>>{
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                        productsList.value = response.body()
                        Log.i("RESPONSE_BODY", responseBody.toString())
                        myDao.insertData(responseBody)


                    } else {
                        myDao.getAllData()
                    }
                }


            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {


            }

        })
    }

    */






