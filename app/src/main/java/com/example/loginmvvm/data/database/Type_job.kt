package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Technician_detail.autoIncrement
import org.jetbrains.exposed.sql.Table

object Type_job : Table("type_job") {
    val type_job_id = integer("type_job_id").autoIncrement()
    val namejob = varchar("namejob", 20)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(type_job_id, name = "type_job_id_PK")
}