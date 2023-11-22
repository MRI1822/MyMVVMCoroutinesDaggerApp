package com.example.mymvvmretrofitcoroutinesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmretrofitcoroutinesapp.R
import com.example.mymvvmretrofitcoroutinesapp.model.Product

class StoreProductsAdapter: RecyclerView.Adapter<StoreProductsViewHolder>() {
    private var products: List<Product> = listOf()

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreProductsViewHolder {
        return StoreProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: StoreProductsViewHolder, position: Int) {
        with(holder.itemView) {
            findViewById<TextView>(R.id.item_title).text = products[position].title
            findViewById<TextView>(R.id.item_category).text = products[position].category
        }
    }
}

class StoreProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)