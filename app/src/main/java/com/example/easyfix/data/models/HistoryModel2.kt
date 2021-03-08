package com.example.easyfix.data.models


data class HistoryModel2(
    //    val order: Int? = null,
//    val adode: String? = null,
//    val repair_List: String? = null,
    val datelong:Long?=null,
    val date: String? = null,
    val sumOrderByDate: Int,
    val orders: List<OrderModeldetail>
)
