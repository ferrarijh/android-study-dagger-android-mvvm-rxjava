package com.example.mydaggerapplication.network

sealed class AuthResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Login<T>(data: T) : AuthResource<T>(data)
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class Error<T>(data: T? = null, message: String) : AuthResource<T>(data, message)
    class LogOut<T>: AuthResource<T>()
}