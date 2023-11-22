package com.example.mymvvmretrofitcoroutinesapp.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmretrofitcoroutinesapp.R
import com.example.mymvvmretrofitcoroutinesapp.app.MyApplication
import com.example.mymvvmretrofitcoroutinesapp.viewmodel.MainViewModel
import com.example.mymvvmretrofitcoroutinesapp.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: StoreProductsAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val errorView: TextView
        get() = findViewById(R.id.error_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val component = (application as MyApplication).appComponent
        component.inject(this)
        mainViewModel = ViewModelProvider(this,mainViewModelFactory)[MainViewModel::class.java]
        productsAdapter = StoreProductsAdapter()

        setupProductsList()
        setupLiveDataObservers()
    }

    private fun setupProductsList() {
        recyclerView = findViewById(R.id.products_list)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
        }
    }

    private fun setupLiveDataObservers() {
        mainViewModel.productsLiveData.observe(this, Observer {
            if(!it.isNullOrEmpty()) {
                recyclerView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                productsAdapter.setProducts(it)
            }
        })

        mainViewModel.errorLiveData.observe(this, Observer {
            recyclerView.visibility = View.GONE
            errorView.visibility = View.VISIBLE
            errorView.text = it
        })
    }
}