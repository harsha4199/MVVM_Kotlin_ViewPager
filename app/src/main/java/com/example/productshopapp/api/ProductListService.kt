package com.example.productshopapp.api

import com.example.productshopapp.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET

interface ProductListService {

    @GET("/products")
    suspend fun getProductList(): Response<ProductsList>


}