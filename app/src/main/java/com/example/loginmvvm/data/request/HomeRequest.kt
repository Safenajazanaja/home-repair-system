package com.example.loginmvvm.data.request

data class HomeRequest(
    val id:Int?=null,
    val home: String? = null,
    val amphurId :Int?=null,
    val provincesId :Int?=null,
    val districtId :Int?=null
)
