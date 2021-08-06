package com.example.loginmvvm.data.models

 data class ProfileModel (
     val username:String,
     val name: String,
     val telephone:String,
     val img:String?=null,
     val abode:String?=null,
//     var geo_id:Int?=null,
//     val geo_name:String?=null,
     var province_id:Int?=null,
     val province_name:String?=null,
     var amphur_id:Int?=null,
     val amphur_name:String?=null,
     var district_id:Int?=null,
     val district_name:String?=null
 )
