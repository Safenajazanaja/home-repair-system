package com.example.loginmvvm.data.request

import java.util.*


data class RepairRequest(
    val userid:Int?=null,
    val abode:String,
    val repair_list:String,
//    val date: Long?=null,
    val latitudeval :Double?=null,
    val longitude:Double?=null,
    val idtypejob:Int?=null

    )
