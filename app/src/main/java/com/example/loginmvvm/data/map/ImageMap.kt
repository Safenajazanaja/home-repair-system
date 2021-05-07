package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Orderl
import com.example.loginmvvm.data.models.ImagModel
import org.jetbrains.exposed.sql.ResultRow

object ImageMap {

    fun toImage(row: ResultRow)= ImagModel(
        img = row[Orderl.image]
    )
}