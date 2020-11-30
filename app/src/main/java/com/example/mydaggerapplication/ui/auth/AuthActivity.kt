package com.example.mydaggerapplication.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.mydaggerapplication.databinding.ActivityAuthBinding
import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.di.factory.ViewModelFactory
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.ui.dialog.LoadingDialog
import com.example.mydaggerapplication.ui.main.MainActivity
import com.example.mydaggerapplication.user.User
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@AuthScope
class AuthActivity : DaggerAppCompatActivity(){
    companion object{
        const val TAG = "AuthActivity"
    }

    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    @Inject lateinit var factory: ViewModelFactory
    private val aViewModel by lazy{ ViewModelProvider(this, factory).get(AuthViewModel::class.java) }

    private val vBinding by lazy{ActivityAuthBinding.inflate(layoutInflater)}

    private val loadingDialog by lazy{ LoadingDialog(this) }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()...")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vBinding.root)

        Log.d(TAG, "onCreate()...")

//        DaggerAppComponent.inject(this)   //you don't need this here anymore with dagger android

        setLogo()
        setViewModel()
        setDialog()
        setObserver()
        setBtn()
    }

    private fun setLogo(){
        requestManager.load(logo)
            .into(vBinding.ivLoginLogo)
    }

    private fun setViewModel(){
        aViewModel.show()
    }

    private fun setDialog(){
        loadingDialog.setCancelable(false)
    }

    private fun setObserver(){
        aViewModel.getAuthState().observe(this){
            when(it){
                is AuthResource.Loading<User> -> {
                    showLoadingDialog(true)
                }
                is AuthResource.Login<User> -> {
                    showLoadingDialog(false)
                    onLoginSuccess()
                    Toast.makeText(baseContext, "Authenticated with email [${it.data?.email}]", Toast.LENGTH_SHORT).show()
                }
                is AuthResource.Error<User> -> {
                    showLoadingDialog(false)
                    Toast.makeText(this, "Error authenticating :(", Toast.LENGTH_SHORT).show()
                }
                is AuthResource.LogOut<User> -> {
                    showLoadingDialog(false)
                }
            }
        }
    }

    private fun setBtn(){
        vBinding.btnLogin.setOnClickListener{
            var inputId = -1
            try {
                inputId = vBinding.tietId.text.toString().toInt()
            }catch(e: NumberFormatException){
                Toast.makeText(this, "Error: input id must be int type", Toast.LENGTH_SHORT).show()
            }
            if(inputId != -1)
                aViewModel.authenticateWithId(inputId)
        }
    }

    private fun showLoadingDialog(toggle: Boolean){
        if (toggle)
            loadingDialog.show()
        else
            loadingDialog.dismiss()
    }

    private fun onLoginSuccess(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
