package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table

object Time: Table("time") {

    val id_time= integer("id_time").autoIncrement()
    val time=varchar("time",20)


}