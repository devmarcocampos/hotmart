package com.example.hotmartapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment (
    @SerializedName("id")
    var id: Int,
    @SerializedName("review")
    var review: Double,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("author")
    var author: String,
    @SerializedName("origin")
    var origin: String
) : Serializable

data class ResponseComment(
    @SerializedName("listComments")
    var listComments: ArrayList<Comment>
)