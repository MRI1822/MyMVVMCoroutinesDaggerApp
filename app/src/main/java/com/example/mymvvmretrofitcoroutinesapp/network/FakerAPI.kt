package com.example.mymvvmretrofitcoroutinesapp.network

import com.example.mymvvmretrofitcoroutinesapp.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface FakerAPI {
    @GET("products")
    suspend fun getProducts() : Response<List<Product>>
}