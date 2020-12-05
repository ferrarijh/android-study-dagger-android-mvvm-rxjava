package com.example.mydaggerapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class Post(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("userId")
    @Expose
    val userId: Int,

    @SerializedName("title")
    @Expose
    val body: String
)