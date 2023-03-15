package com.example.shopdeneme.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.repo.ProductsRepository
import kotlinx.coroutines.launch

class BaseViewModel(context : Context) : ViewModel() {





    private var mainRepo  = ProductsRepository(context)


    private var _products = MutableLiveData<List<Products>>()
    val products : LiveData<List<Products>>
    get() = _products

    private var _top10products = MutableLiveData<List<Products>>()
    val  top10products : LiveData<List<Products>>
    get() = _top10products


    init {
        this.getTop10AllData()
        this.getAllData()
    }

    private  fun getTop10AllData(){
        viewModelScope.launch {
            mainRepo.products()
            _top10products = mainRepo.top10productsList
        }
    }

    private fun getAllData(){
        viewModelScope.launch {
            mainRepo.products()
            _products = mainRepo.productsList
        }
    }





}