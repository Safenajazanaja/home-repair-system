package com.example.loginmvvm.data.request

data class SingupRequest(
    val username:String,
    val fullname:String,
    val phone:String,
    val password:String,
    val repassword:String
)
