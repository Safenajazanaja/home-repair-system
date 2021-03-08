package com.example.easyfix.data.map

import com.example.easyfix.data.database.Type_technician
import com.example.easyfix.data.models.SeletTypejobModel
import org.jetbrains.exposed.sql.ResultRow

object SeletTypejobMap {
    fun toSeletTypejob(row: ResultRow) = SeletTypejobModel(
        id = row[Type_technician.type_engineer],
        type = row[Type_technician.name_engineer]

    )
}