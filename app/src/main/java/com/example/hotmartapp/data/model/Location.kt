package com.example.hotmartapp.data.model

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("review")
    var review: Double,
    @SerializedName("type")
    var type: String
)

data class ResponseLocation(
    @SerializedName("listLocations")
    var listLocations: ArrayList<Location>
)