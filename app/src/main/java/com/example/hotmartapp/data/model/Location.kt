package com.example.hotmartapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("review")
    var review: Double,
    @SerializedName("type")
    var type: String,
    var image: Image
) : Serializable

data class ResponseLocation(
    @SerializedName("listLocations")
    var listLocations: ArrayList<Location>
)

data class LocationDetails(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("review")
    var review: Double,
    @SerializedName("type")
    var type: String,
    @SerializedName("about")
    var about: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("adress")
    var adress: String,
    @SerializedName("schedule")
    var schedule: Any

// @SerializedName("schedule")
//    var schedule: ArrayList<Schedule>,
)

data class Schedule(
    @SerializedName("sunday")
    var sunday: Day?,
    @SerializedName("monday")
    var monday: Day?,
    @SerializedName("tuesday")
    var tuesday: Day?,
    @SerializedName("wednesday")
    var wednesday: Day?,
    @SerializedName("thursday")
    var thursday: Day?,
    @SerializedName("friday")
    var friday: Day?,
    @SerializedName("saturday")
    var saturday: Day?

)

data class Day(
    @SerializedName("open")
    var open: String,
    @SerializedName("close")
    var close: String,
    var name: String
)