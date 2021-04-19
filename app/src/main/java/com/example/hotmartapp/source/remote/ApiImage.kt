package com.example.hotmartapp.source.remote

import com.example.hotmartapp.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiImage {
    @GET(".")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String
    ): ImageResponse
}