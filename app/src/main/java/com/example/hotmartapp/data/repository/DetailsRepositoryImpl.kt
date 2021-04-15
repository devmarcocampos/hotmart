package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.LocationDetails
import com.example.hotmartapp.data.model.ResponseLocation
import com.example.hotmartapp.source.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepositoryImpl(
    private val api: Api
) : DetailsRepository {
    override suspend fun getLocationDetails(id: Int): LocationDetails =
        withContext(Dispatchers.IO) {
            api.getLocationDetails(id)
        }
}