package com.example.mydaggerapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mydaggerapplication.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    companion object{
        const val TAG = "SessionManager"
    }

    val cachedUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(source: LiveData<AuthResource<User>>){
        cachedUser.value = AuthResource.Loading(null)
        cachedUser.addSource(source){
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logOut(){
        cachedUser.value = AuthResource.LogOut()
    }
}