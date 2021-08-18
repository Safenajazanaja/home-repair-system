package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.*
import com.example.loginmvvm.data.models.HistoryDetailModel
import com.example.loginmvvm.data.models.HistoryModel
import com.example.loginmvvm.data.models.OrderModel
import org.jetbrains.exposed.sql.ResultRow

object HistoryMap {
    fun toHistory(row: ResultRow)=HistoryModel(
        order = row[Orderl.order_id],
        adode = row[Orderl.abode],
        repair_List = row[Orderl.repair_list],
        date = row[Orderl.dateLong],
        price = row[Orderl.price],
        status = row[Status.status_name],
        province = row[Province.province_name],
        amphur = row[Amphur.amphur_name],
        district = row[District.district_name]
    )
//    fun toOrder(row: ResultRow)=OrderModel(
//        order = row[Orderl.order_id],
//        adode = row[Orderl.abode],
//        repair_List = row[Orderl.repair_list],
//        date = row[Orderl.dateLong],
//        price = row[Orderl.price]
//    )
//    fun toOrderdetail(row: ResultRow)=HistoryDetailModel(
//        order = row[Orderl.order_id],
//        adode = row[Orderl.abode],
//        repair_List = row[Orderl.repair_list],
//        date = row[Orderl.dateLong],
//        price = row[Orderl.price]
//    )
}