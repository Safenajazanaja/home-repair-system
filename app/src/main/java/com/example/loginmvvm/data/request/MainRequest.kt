package com.example.loginmvvm.data.request

import org.joda.time.DateTime

data class MainRequest(
    val user_id:Int?=null,
    val abode:String?=null,
    val repair_list:String?=null,
    val pay_type:Int?=null,
    val date:DateTime?=null,
    val price:Int?=null

)
