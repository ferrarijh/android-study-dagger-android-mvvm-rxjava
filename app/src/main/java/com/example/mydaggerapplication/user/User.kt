package com.example.mydaggerapplication.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("username")
    @Expose
    val username: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null
)