package com.example.mydaggerapplication.di.module

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydaggerapplication.network.MainApi
import com.example.mydaggerapplication.ui.main.MainActivity
import com.example.mydaggerapplication.ui.main.posts.PostsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class MainModule{
    companion object{
        @JvmStatic
        @Provides
        fun provideMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

        @JvmStatic
        @Provides
        fun providePostsAdapter(): PostsAdapter = PostsAdapter()

        @JvmStatic
        @Provides
        fun provideLinearLayoutManager(activity: MainActivity): LinearLayoutManager{
            return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}