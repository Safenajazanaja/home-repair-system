package com.example.loginmvvm.data.datasource

import com.example.loginmvvm.data.database.User
import com.example.loginmvvm.data.request.LoginRequest
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object DataSource {

    fun login(request: LoginRequest): Boolean {
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
            return true
        } else {
            return false
        }
    }

}