package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.District.autoIncrement
import org.jetbrains.exposed.sql.Table

object Geography:Table("geography") {
    val geo_id= integer("geo_id").autoIncrement()
    val geo_name= varchar("geo_name",255)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Geography.geo_id, name = "geo_id_PK")

}