package com.example.mydaggerapplication.di.module

import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.di.annotation.MainScope
import com.example.mydaggerapplication.ui.auth.AuthActivity
import com.example.mydaggerapplication.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules=[AuthViewModelModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    //add additional activities here annotated with @ContributesAndroidInjector
    @MainScope
    @ContributesAndroidInjector(modules=[FragmentBuildersModule::class, MainViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}