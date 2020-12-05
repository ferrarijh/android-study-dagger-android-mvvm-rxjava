package com.example.mydaggerapplication.network

import com.example.mydaggerapplication.model.Post
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPosts(@Query("userId")userId: Int): Flowable<List<Post>>
}