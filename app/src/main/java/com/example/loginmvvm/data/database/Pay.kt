package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Orderl_detail.autoIncrement
import com.example.loginmvvm.data.database.Orderl_detail.references
import org.jetbrains.exposed.sql.Table

object Pay:Table("pay") {
    val pay_id = integer("pay_id").autoIncrement()
    val pay_type= varchar("pay_type",20)


    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(pay_id, name = "pay_id_PK")
}