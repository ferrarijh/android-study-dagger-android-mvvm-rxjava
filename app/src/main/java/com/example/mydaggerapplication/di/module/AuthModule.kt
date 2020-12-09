package com.example.mydaggerapplication.di.module

import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.network.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthModule {
    companion object {

        @AuthScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)
    }
}