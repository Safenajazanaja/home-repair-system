package com.example.easyfix.data.map

import com.example.easyfix.data.database.Orderl
import com.example.easyfix.data.models.HistoryDetailModel
import com.example.easyfix.data.models.HistoryModel
import com.example.easyfix.data.models.OrderModel
import org.jetbrains.exposed.sql.ResultRow

object HistoryMap {
    fun toHistory(row: ResultRow)=HistoryModel(
        order = row[Orderl.order_id],
        adode = row[Orderl.abode],
        repair_List = row[Orderl.repair_list],
        date = row[Orderl.dateLong]
    )
    fun toOrder(row: ResultRow)=OrderModel(
        order = row[Orderl.order_id],
        adode = row[Orderl.abode],
        repair_List = row[Orderl.repair_list],
        date = row[Orderl.dateLong],
//        price = row[Orderl.price]
    )
    fun toOrderdetail(row: ResultRow)=HistoryDetailModel(
        order = row[Orderl.order_id],
        adode = row[Orderl.abode],
        repair_List = row[Orderl.repair_list],
        date = row[Orderl.dateLong],
        price = row[Orderl.price]
    )
}