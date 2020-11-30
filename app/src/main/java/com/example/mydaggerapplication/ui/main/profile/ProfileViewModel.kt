package com.example.mydaggerapplication.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.di.annotation.MainScope
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.network.SessionManager
import com.example.mydaggerapplication.user.User
import javax.inject.Inject

@MainScope
class ProfileViewModel @Inject constructor(
    val sessionManager: SessionManager
) : ViewModel() {

    fun getUser(): LiveData<AuthResource<User>>{
        return sessionManager.cachedUser
    }
}