package com.example.productshopapp.models

data class ProductsList(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)