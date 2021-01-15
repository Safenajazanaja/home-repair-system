package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date


object Orderl: Table("orderl"){


    val order_id = integer("order_id").autoIncrement()
    val user_id= integer("user_id")
    val abode= varchar("abode",100)
    val repair_list= varchar("repair_list",50)
    val pay_type= integer("pay_type")
    val date=date("date")
    val price=integer("pricem")
//    val employee_id=integer("employee_id")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(order_id, name = "order_id_PK")

}