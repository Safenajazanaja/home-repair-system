package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Amphur
import com.example.loginmvvm.data.database.Province
import com.example.loginmvvm.data.models.AmphurModel
import com.example.loginmvvm.data.models.ProvincesModel
import org.jetbrains.exposed.sql.ResultRow

object AmphurMap {
    fun toAmphur(row: ResultRow) = AmphurModel(
        id = row[Amphur.amphur_id],
        name = row[Amphur.amphur_name],
        )
}


