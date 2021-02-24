package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Technician.autoIncrement
import org.jetbrains.exposed.sql.Table

object Technician_detail:Table("technician_detail") {
    val id_technician_detail= integer("id_technician_detail").autoIncrement()
    val id_technician= integer("id_technician")
    val id_type_technician = integer("fid_type_technician")
}