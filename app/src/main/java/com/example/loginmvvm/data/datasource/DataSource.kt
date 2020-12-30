package com.example.loginmvvm.data.datasource

import com.example.loginmvvm.data.database.User
import com.example.loginmvvm.data.request.LoginRequest
import com.example.loginmvvm.data.response.LoginResponse
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object DataSource {

//    fun login(request: LoginRequest): LoginResponse {
//        val result = transaction {
//            addLogger(StdOutSqlLogger)
//
//            User.select {
//                User.username eq request.username
//            }
//                .andWhere { User.password eq request.password }
//                .count()
//                .toInt()
//
//        }
//
//        if (result == 1) {
//            User.selectAll().andWhere { User.username eq request.username  }.andWhere { User.password eq request.password }
//            return LoginResponse(userId = User.user_id,sessec = true)
//        } else {
//            return LoginResponse(null,sessec = true)
//        }
//    }

    fun login(request: LoginRequest): LoginResponse {
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
            return LoginResponse(userId = userId, sessec = true)
        } else {
            return LoginResponse(null, sessec = false)
        }
    }

}