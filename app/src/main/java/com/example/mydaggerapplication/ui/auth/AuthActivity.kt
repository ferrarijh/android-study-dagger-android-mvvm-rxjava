package com.example.mydaggerapplication.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.mydaggerapplication.R
import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.di.factory.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    @Inject lateinit var factory: ViewModelFactory
    private val aViewModel by lazy{ ViewModelProvider(this, factory).get(AuthViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

//        DaggerAppComponent.inject(this)   //you don't need this here anymore with dagger android

        setLogo()
        setViewModel()
    }

    private fun setLogo(){
        requestManager.load(logo)
            .into(findViewById(R.id.login_logo))
    }

    private fun setViewModel(){
        aViewModel.show()
    }
}