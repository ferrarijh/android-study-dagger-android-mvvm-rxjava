package com.example.mydaggerapplication.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.network.SessionManager
import com.example.mydaggerapplication.network.AuthApi
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.model.User
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    val authApi: AuthApi,
    val sessionManager: SessionManager
) : ViewModel() {
    companion object{
        const val TAG = "AuthViewModel"
    }

    init{
        Log.d(TAG, "$this")
    }

    fun authenticateWithId(id: Int){
        sessionManager.authenticateWithId(queryUserId(id))
    }

    private fun queryUserId(id: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id)
                        .onErrorReturn{
                            it.printStackTrace()
                            User(id=-1)
                        }
                        .map{
                            if(it.id == -1)
                                AuthResource.Error(it, "Invalid user")
                            else
                                AuthResource.Login(it)
                        }
                        .subscribeOn(Schedulers.io())
        )
    }

    fun getAuthState(): LiveData<AuthResource<User>>{
        return sessionManager.cachedUser
    }

    fun show(){
        Log.d(TAG, "I'm AuthViewModel[$this]")
    }
}