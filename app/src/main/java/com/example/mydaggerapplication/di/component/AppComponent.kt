package com.example.mydaggerapplication.di.component

import android.app.Application
import com.example.mydaggerapplication.network.SessionManager
import com.example.mydaggerapplication.application.BaseApplication
import com.example.mydaggerapplication.di.module.ActivityBuildersModule
import com.example.mydaggerapplication.di.module.AppModule
import com.example.mydaggerapplication.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,  //this class will be set as module ONLY at application level
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent: AndroidInjector<BaseApplication> {  //'BaseApplication' injected to 'AppComponent'

    //fun inject(activity: MainActivity)    //you don't need this anymore with dagger android

    fun sessionManager(): SessionManager

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): AppComponent
    }
}