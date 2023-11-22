package com.example.mymvvmretrofitcoroutinesapp.di

import com.example.mymvvmretrofitcoroutinesapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}