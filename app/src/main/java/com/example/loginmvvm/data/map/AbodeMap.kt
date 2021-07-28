package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Users
import com.example.loginmvvm.data.models.AbodeModel
import org.jetbrains.exposed.sql.ResultRow

object AbodeMap {
    fun toAbode(row: ResultRow)=AbodeModel(
        abode = row[Users.abode]
    )
}