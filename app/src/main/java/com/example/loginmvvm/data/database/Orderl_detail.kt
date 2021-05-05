package com.example.loginmvvm.data.database

import com.example.loginmvvm.data.database.Orderl.autoIncrement
import com.example.loginmvvm.data.database.Orderl.references
import org.jetbrains.exposed.sql.Table

object Orderl_detail:Table("order_detail") {
    val orderl_detail_id = integer("orderl_detail_id").autoIncrement()
    val orderl_id= integer("orderl_id").references(Orderl.order_id)
    val material_id= integer("material_id").references(Material.material_id)
    val qty=integer("qty")


    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(orderl_detail_id, name = "order_id_PK")
}