package com.example.mydaggerapplication.di.module

import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.di.annotation.ViewModelKey
import com.example.mydaggerapplication.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(vm: AuthViewModel): ViewModel
}