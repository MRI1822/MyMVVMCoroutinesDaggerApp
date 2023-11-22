package com.example.mymvvmretrofitcoroutinesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmretrofitcoroutinesapp.model.Product
import com.example.mymvvmretrofitcoroutinesapp.repository.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: ProductRepo):ViewModel() {

    val productsLiveData: LiveData<List<Product>>
        get() = repo.products
    val errorLiveData: LiveData<String>
        get() = repo.errorMessage
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchProducts()
        }
    }
}