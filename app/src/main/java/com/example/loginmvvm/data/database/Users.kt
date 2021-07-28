package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table

object Users : Table("user") {
    val user_id = integer("user_id").autoIncrement()
    val username = varchar("username", 20)
    val password = varchar("password", 20)
    val fullname = varchar("fullname", 30)
    val phone = varchar("phone", 10)
    val image =varchar("image",1000)
    val abode=varchar("abode",50)


    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(user_id, name = "user_id_PK")


}