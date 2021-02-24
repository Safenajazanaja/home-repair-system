package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Technician
import com.example.loginmvvm.data.models.EngineerSeletModel
import org.jetbrains.exposed.sql.ResultRow

object EngineerSeletMap {
    fun toEngineerSelet(row: ResultRow)=EngineerSeletModel(
        technician_id = row[Technician.technician_id],
        fullname = row[Technician.fullname]

    )
}