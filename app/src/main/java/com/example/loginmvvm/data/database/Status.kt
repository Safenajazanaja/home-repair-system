package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Orderl.autoIncrement
import org.jetbrains.exposed.sql.Table

object Status:Table("status") {
    val status_id = integer("status_id").autoIncrement()
    val status_name= varchar("status_name",20)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(status_id, name = "status_id_PK")
}