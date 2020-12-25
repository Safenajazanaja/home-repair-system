package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object User : Table("user") {
    val user_id = integer("user_id").autoIncrement()
    val username = varchar("username", 20)
    val password = varchar("password", 20)
//    val fullname = varchar("fullname", 40)
//    val phone = varchar("phone", 10)
//    val authority = integer("authority")
//    val technicianId = integer("technicianId")
//    val active = integer("active")
//    val createBy = varchar("createBy", 50)
//    val createDate = datetime("createDate")
//    val updateBy = varchar("updateBy", 50)
//    val updateDate = datetime("updateDate")


    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(user_id, name = "user_id")


}