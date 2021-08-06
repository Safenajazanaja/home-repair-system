package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Orderl_detail.references
import com.example.loginmvvm.data.database.Pay.autoIncrement
import org.jetbrains.exposed.sql.Table

object Provinces:Table("province") {
    val province_id = integer("province_id").autoIncrement()
    val province_code=varchar("province_code",2)
    val province_name= varchar("province_name",150)
    val geo_id=integer("province_name").references(Geography.geo_id)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Provinces.province_id, name = "province_id_PK")


}