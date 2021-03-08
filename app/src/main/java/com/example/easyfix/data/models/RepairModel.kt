package com.example.easyfix.data.models

import org.joda.time.DateTime

data class RepairModel(
    val date: DateTime,
    val abode:String,
    val repair_list:String,
    val order_id:Int
)
