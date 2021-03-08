package com.example.easyfix.data.map


import com.example.easyfix.data.database.Users
import com.example.easyfix.data.models.ProfileModel
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow) = ProfileModel(
        username = row[Users.username],
        name = row[Users.fullname],
        telephone = row[Users.phone],
    )
}