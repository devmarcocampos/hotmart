package com.example.hotmartapp.source.remote

import com.example.hotmartapp.data.model.ResponseLocation
import retrofit2.http.GET

interface Api {
    @GET("locations")
    suspend fun getLocations(): ResponseLocation
}