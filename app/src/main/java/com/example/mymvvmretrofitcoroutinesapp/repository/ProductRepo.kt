package com.example.mymvvmretrofitcoroutinesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymvvmretrofitcoroutinesapp.model.Product
import com.example.mymvvmretrofitcoroutinesapp.network.FakerAPI
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ProductRepo @Inject constructor(private val fakerAPI: FakerAPI) {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products
    val errorMessage = MutableLiveData<String>()

    suspend fun fetchProducts() {
        try {
            val result: Response<List<Product>> = fakerAPI.getProducts()
            if (result.isSuccessful && result.body() != null) {
                _products.postValue(result.body())
            }
        } catch (e: HttpException) {
            errorMessage.postValue("There was a network error")
        } catch (e: Throwable) {
            errorMessage.postValue("There was an error. Please, try again later")
        }
    }

}