package com.example.hotmartapp.ui.main

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(networkModule(), networkImageModule(), imageRepositoryModule, mainRepositoryModule, mainViewModelModule, detailsRepositoryModule, detailsViewModelModule))
//            modules(listOf(retrofitModule, apiModule, mainRepositoryModule, mainViewModelModule, detailsRepositoryModule, detailsViewModelModule))
        }
    }
}