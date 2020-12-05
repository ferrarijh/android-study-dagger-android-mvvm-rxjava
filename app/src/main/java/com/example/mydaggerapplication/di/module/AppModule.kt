package com.example.mydaggerapplication.di.module

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.mydaggerapplication.R
import com.example.mydaggerapplication.network.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {
    companion object{
        @Provides
        fun provideRequestOptions(): RequestOptions = RequestOptions
            .placeholderOf(R.drawable.ic_android_black_100dp)
            .error(R.drawable.ic_android_black_100dp)

        @Provides
        fun provideGlideInstance(app: Application, options: RequestOptions): RequestManager{
            return Glide.with(app)
                .setDefaultRequestOptions(options)
        }

        @Provides
        fun provideAppDrawable(app: Application): Drawable{
            return ContextCompat.getDrawable(app, R.drawable.jetpack_logo)!!
        }


        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }
}