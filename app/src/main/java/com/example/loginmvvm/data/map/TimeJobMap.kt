package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Time
import com.example.loginmvvm.data.models.TimeJobModel
import org.jetbrains.exposed.sql.ResultRow

object TimeJobMap {
    fun toTimeJob(row: ResultRow)=TimeJobModel(
        id = row[Time.id_time],
        time = row[Time.time]
    )
}