package com.example.mydaggerapplication.ui

import android.content.Intent
import android.util.Log
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.network.SessionManager
import com.example.mydaggerapplication.ui.auth.AuthActivity
import com.example.mydaggerapplication.model.User
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

//for all the activities EXCEPT AuthActivity
open class BaseActivity: DaggerAppCompatActivity() {

    @Inject protected lateinit var sessionManager: SessionManager

    companion object{
        const val TAG = "BaseActivity"
    }

    protected fun setAuthObserver(){
        sessionManager.cachedUser.observe(this){
            when(it){
                is AuthResource.Loading<User> -> { }
                is AuthResource.Login<User> -> {
                    Log.d(TAG, "Authenticated with email [${it.data?.email}]")
                }
                is AuthResource.Error<User> -> {
                    Log.d(TAG, "Error authenticating")
                }
                is AuthResource.LogOut<User> -> {
                    goToAuth()
                }
            }
        }
    }

    private fun goToAuth(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
