package com.example.productshopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productshopapp.api.ProductListService
import com.example.productshopapp.api.RetrofitHelper
import com.example.productshopapp.models.ProductsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val productListLiveData: MutableLiveData<ProductsList> = MutableLiveData()

    fun getProductListObserver(): MutableLiveData<ProductsList> {
        return productListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroinstance = RetrofitHelper.getInstance().create(ProductListService::class.java)
            val response = retroinstance.getProductList()
            productListLiveData.postValue(response.body()!!)


        }
    }
}


