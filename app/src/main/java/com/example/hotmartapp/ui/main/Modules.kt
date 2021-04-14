package com.example.hotmartapp.ui.main

import com.example.hotmartapp.data.repository.MainRepository
import com.example.hotmartapp.data.repository.MainRepositoryImpl
import com.example.hotmartapp.source.remote.Api
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val mainRepositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}

val apiModule = module {
    fun providesApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    single { providesApi(get()) }
}

val retrofitModule = module {
    fun providesRetrofit(): Retrofit {
        val BASE_URL = "https://hotmart-mobile-app.herokuapp.com/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { providesRetrofit() }
}