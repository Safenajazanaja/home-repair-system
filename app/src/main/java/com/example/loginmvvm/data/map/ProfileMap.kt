package com.example.loginmvvm.data.map


import com.example.loginmvvm.data.database.Users
import com.example.loginmvvm.data.models.ProfileModel
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow) = ProfileModel(
        username = row[Users.username],
        name = row[Users.fullname],
        telephone = row[Users.phone],
        img = row[Users.image]
    )
}