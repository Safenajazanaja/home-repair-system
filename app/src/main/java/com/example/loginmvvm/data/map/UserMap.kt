package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.User
import com.example.loginmvvm.data.models.UserModel
import org.jetbrains.exposed.sql.ResultRow

object UserMap {
    fun toUserMap(row: ResultRow)= UserModel(
        userId = row[User.user_id],
    )
}