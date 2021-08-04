package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Provinces
import com.example.loginmvvm.data.database.Users
import com.example.loginmvvm.data.models.ProvincesModel
import org.jetbrains.exposed.sql.ResultRow

object ProvincesMap {
    fun toProvinces(row: ResultRow) = ProvincesModel(
        id = row[Provinces.id_provinces],
        name = row[Provinces.name],

    )
}