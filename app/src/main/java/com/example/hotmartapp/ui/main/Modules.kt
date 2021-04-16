package com.example.hotmartapp.ui.main

import com.example.hotmartapp.data.repository.*
import com.example.hotmartapp.source.remote.Api
import com.example.hotmartapp.source.remote.ApiImage
import com.example.hotmartapp.ui.details.DetailsViewModel
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


//
val imageRepositoryModule = module {
    single<ImageRepository> { ImageRepositoryImpl(get()) }
}
//


//val apiModule = module {
//    fun providesApi(retrofit: Retrofit): Api =
//        retrofit.create(Api::class.java)
//
//    single { providesApi(get()) }
//}
//
//val retrofitModule = module {
//    fun providesRetrofit(): Retrofit {
//        val BASE_URL = "https://hotmart-mobile-app.herokuapp.com/"
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    single { providesRetrofit() }
//}




/////////////////////////////////////////

//val apiModuleImage = module {
//    fun providesApi(retrofit: Retrofit): ApiImage =
//            retrofit.create(ApiImage::class.java)
//
//    single { providesApi(get()) }
//}
//
//val retrofitImageModule = module {
//    fun providesRetrofit(): Retrofit {
//        val BASE_URL = "https://pixabay.com/api/"
//
//        return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }
//
//    single { providesRetrofit() }
//}

//

fun networkModule() = module {
    val BASE_URL = "https://hotmart-mobile-app.herokuapp.com/"

    fun providesApi(): Api {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(Api::class.java)

    }

    single { providesApi() }
}

fun networkImageModule() = module {
    val BASE_URL = "https://pixabay.com/api/"

    fun providesApi(): ApiImage {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ApiImage::class.java)

    }

    single { providesApi() }
}