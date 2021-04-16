package com.example.hotmartapp.data.repository

import com.example.hotmartapp.data.model.ImageResponse

interface ImageRepository {
    suspend fun getImages(): ImageResponse

    suspend fun getFoods(): ImageResponse
}