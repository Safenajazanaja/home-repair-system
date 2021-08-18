package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Orderl
import com.example.loginmvvm.data.database.Status
import com.example.loginmvvm.data.database.Time
import com.example.loginmvvm.data.database.Type_job
import com.example.loginmvvm.data.models.ManageModel
import org.jetbrains.exposed.sql.ResultRow

object ManageMap {

    fun toManageMap(row: ResultRow) = ManageModel(
        order_id = row[Orderl.order_id],
        abode = row[Orderl.abode],
        repair_list = row[Orderl.repair_list],
        date = row[Orderl.dateLong],
        latitudeval = row[Orderl.latitude],
        longitude = row[Orderl.longitude],
        typejob = row[Type_job.namejob],
        timezone = row[Time.time],
        idtime = row[Orderl.idtime],
        status = row[Status.status_name]
        )
}