package com.example.mydaggerapplication.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.network.SessionManager
import com.example.mydaggerapplication.model.User
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val sessionManager: SessionManager
) : ViewModel() {

    companion object{
        const val TAG = "ProfileViewModel"
    }

    init {
        Log.d(TAG, "$this")
    }

    fun getUser(): LiveData<AuthResource<User>>{
        return sessionManager.cachedUser
    }
}