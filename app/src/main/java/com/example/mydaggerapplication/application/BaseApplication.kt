package com.example.mydaggerapplication.application

import com.example.mydaggerapplication.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
        //return DaggerAppComponent.builder().application(this).build() //when using builder
    }
}