package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.ResponseLocation

interface MainRepository {
    suspend fun getLocations(): ResponseLocation
}