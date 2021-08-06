package com.example.loginmvvm.data.map


import com.example.loginmvvm.data.database.*
import com.example.loginmvvm.data.models.ProfileModel
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow) = ProfileModel(
        username = row[Users.username],
        name = row[Users.fullname],
        telephone = row[Users.phone],
        img = row[Users.image],
        abode = row[Users.abode],
//        geo_id = row[Users.geo_id],
//        geo_name = row[Geography.geo_name],
        province_id = row[Users.province_id],
        province_name = row[Province.province_name],
        amphur_id = row[Users.amphur_id],
        amphur_name = row[Amphur.amphur_name],
        district_id = row[Users.district_id],
        district_name = row[District.district_name],
    )
}