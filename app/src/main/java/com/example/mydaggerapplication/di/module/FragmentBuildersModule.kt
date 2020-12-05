package com.example.mydaggerapplication.di.module

import com.example.mydaggerapplication.ui.main.posts.PostsFragment
import com.example.mydaggerapplication.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}