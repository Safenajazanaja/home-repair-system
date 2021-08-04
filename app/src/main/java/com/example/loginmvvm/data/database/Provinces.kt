package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Pay.autoIncrement
import org.jetbrains.exposed.sql.Table

object Provinces:Table("provinces") {
    val id_provinces = integer("id").autoIncrement()
    val name= varchar("name_th",150)
}