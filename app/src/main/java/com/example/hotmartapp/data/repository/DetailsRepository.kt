package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.LocationDetails

interface DetailsRepository {
    suspend fun getLocationDetails(id: Int): LocationDetails
}