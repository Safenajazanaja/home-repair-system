package com.example.loginmvvm.data.map

import com.example.loginmvvm.data.database.Material
import com.example.loginmvvm.data.database.Orderl_detail
import com.example.loginmvvm.data.models.ListModel
import org.jetbrains.exposed.sql.ResultRow

object ListMap {
    fun tolist(row: ResultRow)=ListModel(
        id = row[Material.material_id],
        name = row[Material.material_name],
        price = row[Orderl_detail.qty],
        Unitprice = row[Material.price_material],
        qty = row[Orderl_detail.qty]

    )
}