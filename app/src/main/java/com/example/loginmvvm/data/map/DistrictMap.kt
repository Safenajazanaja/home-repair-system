package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.District
import com.example.loginmvvm.data.models.DistrictModel
import org.jetbrains.exposed.sql.ResultRow

object DistrictMap {
    fun toDistrict(row: ResultRow)=DistrictModel(
        id = row[District.district_id],
        name = row[District.district_name]
    )
}