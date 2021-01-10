package com.example.loginmvvm.data.datasource

import com.example.loginmvvm.data.database.Orderl
import com.example.loginmvvm.data.database.User
import com.example.loginmvvm.data.map.ProfileMap
import com.example.loginmvvm.data.models.ProfileModel
import com.example.loginmvvm.data.request.LoginRequest
import com.example.loginmvvm.data.request.MainRequest
import com.example.loginmvvm.data.request.SingupRequest
import com.example.loginmvvm.data.response.LoginResponse
import com.example.loginmvvm.data.response.MainResponse
import com.example.loginmvvm.data.response.SingupResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DataSource {

    fun login(request: LoginRequest): LoginResponse {
        val response = LoginResponse()
        val result = transaction {
            addLogger(StdOutSqlLogger)

            User.select {
                User.username eq request.username
            }
                .andWhere { User.password eq request.password }
                .count()
                .toInt()

        }

        if (result == 1) {
            val userId = transaction {
                User
                    .select { User.username eq request.username }
                    .andWhere { User.password eq request.password }
                    .map { it[User.user_id] }
                    .single()
            }
            response.userId = userId
            response.success = true
        } else {
            response.success = false
        }
        return response
    }

    fun sing_up(request: SingupRequest): SingupResponse {
        val response = SingupResponse()

        val result = transaction {
            addLogger(StdOutSqlLogger)

            User.select {
                User.username eq request.username
            }
                .count()
                .toInt()

        }
        if (result == 1) {
            response.message = "Usernameซ้ำ"
            response.success = false
        } else {
            transaction {
                addLogger(StdOutSqlLogger)

                User.insert {
                    it[username] = request.username.toString()
                    it[fullname] = request.fullname.toString()
                    it[phone] = request.phone.toString()
                    it[password] = request.password.toString()
                }
            }
            response.success = true
            response.message = "สมัครสำเร็จ"
        }
        return response


    }

//    fun insertRepair(request:MainRequest):MainResponse{
//        val response=MainResponse()
//        val putid = LoginResponse()
//
//        val statement= transaction {
//            addLogger(StdOutSqlLogger)
//
//            Orderl.insert {
//                it[user_id]=putid.userId.toString().toInt()
//                it[employee_id]=0
//                it[abode]=request.abode.toString()
//                it[repair_list]=request.repair_list.toString()
//                it[pay_type]=request.pay_type.toString().toInt()
//                it[date]= request.date.toString()
//                it[price]=request.price.toString().toInt()
//            }
//        }
//    }

    fun profile(userId:Int):ProfileModel{
        return  transaction {
            addLogger(StdOutSqlLogger)
            User.slice(User.username,User.fullname,User.phone)
                .select { User.user_id eq userId }
                .map { ProfileMap.toProfile(it) }
                .single()
        }
    }


}