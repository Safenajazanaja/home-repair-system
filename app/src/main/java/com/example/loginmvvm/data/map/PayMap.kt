package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Orderl
import com.example.loginmvvm.data.database.Pay
import com.example.loginmvvm.data.models.ImagModel
import com.example.loginmvvm.data.models.PayModel
import org.jetbrains.exposed.sql.ResultRow

object PayMap {

    fun toPay(row: ResultRow)= PayModel(
        pay = row[Pay.pay_type]
    )
}