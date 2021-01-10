package com.example.loginmvvm.data.map


import com.example.loginmvvm.data.database.User
import com.example.loginmvvm.data.models.ProfileModel
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfile(row: ResultRow)=ProfileModel(
        userId = row[User.user_id],
        name = row[User.fullname],
        telephone = row[User.phone],
        username = row[User.username]
    )



}