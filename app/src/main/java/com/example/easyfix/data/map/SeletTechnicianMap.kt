package com.example.easyfix.data.map

import com.example.easyfix.data.database.Technician
import com.example.easyfix.data.database.Type_technician
import com.example.easyfix.data.models.SeletTechnicanModel
import org.jetbrains.exposed.sql.ResultRow

object SeletTechnicianMap {
    fun toselettechnician(row: ResultRow)=SeletTechnicanModel(
        idtechnican = row[Technician.technician_id],
        nametechnican = row[Technician.fullname],
//        typetechnican = row[Type_technician.name_engineer]
    )
}