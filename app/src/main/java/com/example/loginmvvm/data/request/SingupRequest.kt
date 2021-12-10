package com.example.loginmvvm.data.request

data class SingupRequest(
    val username:String,
    val fullname:String,
    val phone:String,
    val password:String,
    val repassword:String,
    val amphurId :Int,
    val provincesId :Int,
    val districtId :Int,
    val home:String
)
