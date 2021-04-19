package com.example.hotmartapp.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.hotmartapp.data.repository.*
import com.example.hotmartapp.source.remote.Api
import com.example.hotmartapp.source.remote.ApiImage
import com.example.hotmartapp.ui.details.DetailsViewModel
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}

val mainRepositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}

val detailsViewModelModule = module {
    viewModel {
        DetailsViewModel(get(), get())
    }
}

val detailsRepositoryModule = module {
    single<DetailsRepository> { DetailsRepositoryImpl(get()) }
}

val imageRepositoryModule = module {
    single<ImageRepository> { ImageRepositoryImpl(get()) }
}

val ctx = MyApp.applicationContext()

val cacheSize = (5 * 1024 * 1024).toLong()
val myCache = Cache( ctx.cacheDir, cacheSize)

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun provideOfflineCacheInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        var request = chain.request()
        var cacheHeaderValue = if (!hasNetwork(context)!!){
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1
        } else {
            "public, max-age=" + 5
        }
        request = request.newBuilder().header("Cache-Control", cacheHeaderValue).build()
        chain.proceed(request)
    }
}

fun provideCacheInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        var cacheHeaderValue = if (!hasNetwork(context)!!){
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1
        } else {
            "public, max-age=" + 5
        }

        val response = chain.proceed(request)
        response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheHeaderValue)
                .build()
    }
}

fun networkModule() = module {
    val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor(provideOfflineCacheInterceptor(ctx))
            .addNetworkInterceptor(provideCacheInterceptor(ctx))
            .build()

    val BASE_URL = "https://hotmart-mobile-app.herokuapp.com/"

    fun providesApi(): Api {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(Api::class.java)

    }

    single { providesApi() }
}

fun networkImageModule() = module {
    val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor(provideOfflineCacheInterceptor(ctx))
            .addNetworkInterceptor(provideCacheInterceptor(ctx))
            .build()

    val BASE_URL = "https://pixabay.com/api/"

    fun providesApi(): ApiImage {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ApiImage::class.java)

    }

    single { providesApi() }
}