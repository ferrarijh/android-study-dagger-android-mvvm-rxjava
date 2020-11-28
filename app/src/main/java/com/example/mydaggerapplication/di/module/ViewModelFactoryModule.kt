package com.example.mydaggerapplication.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.mydaggerapplication.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}