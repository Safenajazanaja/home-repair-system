package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Orderl
import com.example.loginmvvm.data.models.StatusModel
import org.jetbrains.exposed.sql.ResultRow

object StatusMap {
    fun toStatus(row: ResultRow)=StatusModel(
        statusid = row[Orderl.status]

    )
}