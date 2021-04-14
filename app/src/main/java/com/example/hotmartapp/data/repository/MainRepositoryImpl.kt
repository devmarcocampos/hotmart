package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.ResponseLocation
import com.example.hotmartapp.source.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private val api: Api
) : MainRepository {
    override suspend fun getLocations(): ResponseLocation =
        withContext(Dispatchers.IO) {
            api.getLocations()
        }
}