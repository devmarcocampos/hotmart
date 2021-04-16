package com.example.hotmartapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageResponse (
        @SerializedName("hits")
        var hits: ArrayList<Image>
)

data class Image (
        @SerializedName("id")
        var id: Int,
        @SerializedName("type")
        var type: String,
        @SerializedName("webformatURL")
        var webformatURL: String
) : Serializable