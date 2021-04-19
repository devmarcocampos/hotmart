package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.ImageResponse
import com.example.hotmartapp.source.remote.ApiImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val apiImage: ApiImage
) : ImageRepository {
    override suspend fun getImages(): ImageResponse =
            withContext(Dispatchers.IO) {
                apiImage.getImages("21181458-137adef2c56a8c3908a099e00", "restaurant", "photo")
            }

    override suspend fun getFoods(): ImageResponse =
            withContext(Dispatchers.IO) {
                apiImage.getImages("21181458-137adef2c56a8c3908a099e00", "food", "photo")
            }
}