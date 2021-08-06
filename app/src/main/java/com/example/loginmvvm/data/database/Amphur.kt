package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Provinces.autoIncrement
import org.jetbrains.exposed.sql.Table

object Amphur:Table("amphur") {
    val amphur_id = integer("amphur_id").autoIncrement()
    val amphur_code = varchar("amphur_code",4)
    val amphur_name	= varchar("amphur_name",150)
    val geo_id= integer("geo_id").references(Geography.geo_id)
    val province_id=integer("province_id").references(Provinces.province_id)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Amphur.amphur_id, name = "amphur_id_PK")
}