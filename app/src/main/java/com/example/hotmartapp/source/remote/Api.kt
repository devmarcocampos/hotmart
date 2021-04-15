package com.example.hotmartapp.source.remote

import com.example.hotmartapp.data.model.LocationDetails
import com.example.hotmartapp.data.model.ResponseLocation
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("locations")
    suspend fun getLocations(): ResponseLocation

    @GET("locations/{id}")
    suspend fun getLocationDetails(
        @Path("id") id: Int
    ): LocationDetails
}