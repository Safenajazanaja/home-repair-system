package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Technician_detail.autoIncrement
import org.jetbrains.exposed.sql.Table

object Type_technician: Table("type_technician") {
    val type_engineer= integer("type_engineer").autoIncrement()
    val name_engineer= varchar("name_engineer",20)
}