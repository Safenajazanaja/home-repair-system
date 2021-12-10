package com.example.loginmvvm.data.database

import org.jetbrains.exposed.sql.Table


object Orderl: Table("orderl"){


    val order_id = integer("order_id").autoIncrement()
    val user_id= integer("user_id").references(Users.user_id)
    val abode= varchar("abode",100)
    val repair_list= varchar("repair_list",50)
    val pay_type= integer("pay_type").references(Pay.pay_id)
//    val pricetec=integer("pricetec")
    val dateLong=long("date_long")
    val price=integer("price")
    val id_technician=integer("id_technician").references(Technician.technician_id)
    val latitude =double("latitude")
    val longitude=double("longitude")
    val type_job=integer("type_job").references(Type_job.type_job_id)
    val date_end=long("date_end")
    val status=integer("status_id").references(Status.status_id)
    val image = varchar("image",1000)
    val idtime=integer("id_time").references(Time.id_time)
    val province_id= integer("province_id").references(Province.province_id)
    val amphur_id= integer("amphur_id").references(Amphur.amphur_id)
    val district_id= integer("district_id").references(District.district_id)
    val period=long("period")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(order_id, name = "order_id_PK")

}