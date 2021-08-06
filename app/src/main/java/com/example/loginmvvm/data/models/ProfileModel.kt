package com.example.loginmvvm.data.models

 data class ProfileModel (
     val username:String,
     val name: String,
     val telephone:String,
     val img:String?=null,
     val abode:String?=null,
     var id_provinces:Int?=null,
     val nameprovinces:String?=null
 )
