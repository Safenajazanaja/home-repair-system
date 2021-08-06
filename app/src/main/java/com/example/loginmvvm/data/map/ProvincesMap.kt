package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Province
import com.example.loginmvvm.data.models.ProvincesModel
import org.jetbrains.exposed.sql.ResultRow

object ProvincesMap {
    fun toProvinces(row: ResultRow) = ProvincesModel(
        id = row[Province.province_id],
        name = row[Province.province_name],

    )
}