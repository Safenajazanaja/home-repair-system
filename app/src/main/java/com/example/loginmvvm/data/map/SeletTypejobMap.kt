package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Type_technician
import com.example.loginmvvm.data.models.SeletTypejobModel
import org.jetbrains.exposed.sql.ResultRow

object SeletTypejobMap {
    fun toSeletTypejob(row: ResultRow) = SeletTypejobModel(
        id = row[Type_technician.type_engineer],
        type = row[Type_technician.name_engineer]

    )
}