package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Type_job
import com.example.loginmvvm.data.models.SeletTypejobModel
import org.jetbrains.exposed.sql.ResultRow

object SeletTypejobMap {
    fun toSeletTypejob(row: ResultRow) = SeletTypejobModel(
        id = row[Type_job.type_job_id],
        type = row[Type_job.namejob]

    )
}