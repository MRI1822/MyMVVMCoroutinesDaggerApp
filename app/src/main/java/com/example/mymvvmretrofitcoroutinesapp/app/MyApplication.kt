package com.example.mymvvmretrofitcoroutinesapp.app

import android.app.Application
import com.example.mymvvmretrofitcoroutinesapp.di.AppComponent
import com.example.mymvvmretrofitcoroutinesapp.di.DaggerAppComponent

class MyApplication:Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}