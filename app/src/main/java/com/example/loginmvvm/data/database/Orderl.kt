package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date
import java.math.BigInteger


object Orderl: Table("orderl"){


    val order_id = integer("order_id").autoIncrement()
    val user_id= integer("user_id")
    val abode= varchar("abode",100)
    val repair_list= varchar("repair_list",50)
    val pay_type= integer("pay_type")
    val date=date("date")
    val dateLong=long("date_long")
    val price=integer("price")
    val employee_id=integer("employee_id")
    val latitude =double("latitude")
    val longitude=double("longitude")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(order_id, name = "order_id_PK")

}