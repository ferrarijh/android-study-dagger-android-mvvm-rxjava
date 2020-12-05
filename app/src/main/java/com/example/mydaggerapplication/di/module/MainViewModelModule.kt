package com.example.mydaggerapplication.di.module

import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.di.annotation.MainScope
import com.example.mydaggerapplication.di.annotation.ViewModelKey
import com.example.mydaggerapplication.ui.main.posts.PostsViewModel
import com.example.mydaggerapplication.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(vm: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(vm: PostsViewModel): ViewModel
}