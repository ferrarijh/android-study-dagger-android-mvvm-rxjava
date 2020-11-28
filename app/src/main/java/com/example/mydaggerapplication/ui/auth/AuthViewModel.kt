package com.example.mydaggerapplication.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.di.annotation.AuthScope
import com.example.mydaggerapplication.network.AuthApi
import com.example.mydaggerapplication.user.User
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AuthScope
class AuthViewModel @Inject constructor(val authApi: AuthApi) : ViewModel() {
    companion object{
        const val TAG = "AuthViewModel"
    }

    @Inject
    fun fetchResult(authApi: AuthApi){
        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<User>{
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: User?) {
                    Log.d(TAG, "${t?.name}/${t?.username}/${t?.email}")
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onComplete() {
                }
            })
    }

    fun show(){
        Log.d(TAG, "I'm AuthViewModel[$this]")
    }
}