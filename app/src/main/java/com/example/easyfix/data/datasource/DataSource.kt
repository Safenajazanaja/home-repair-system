package com.example.easyfix.data.datasource


import com.example.easyfix.data.database.*
import com.example.easyfix.data.map.*
import com.example.easyfix.data.models.*
import com.example.easyfix.data.request.*
import com.example.easyfix.data.response.LoginResponse
import com.example.easyfix.data.response.RepairResponse
import com.example.easyfix.data.response.SingupResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DataSource {

    fun login(request: LoginRequest): LoginResponse {
        val response = LoginResponse()
        val result = transaction {
            addLogger(StdOutSqlLogger)

            Users.select {
                Users.username eq request.username
            }
                .andWhere { Users.password eq request.password }
                .count()
                .toInt()

        }

        if (result == 1) {
            val userId = transaction {
                Users.slice(Users.user_id)
                    .select { Users.username eq request.username }
                    .andWhere { Users.password eq request.password }
                    .map { it[Users.user_id] }
                    .single()
            }
            response.userId = userId
            response.success = true
//            Log.d(ContentValues.TAG, "onActivityCreated: "+userId)
        } else {
            response.success = false
        }
        return response
    }

    fun sing_up(request: SingupRequest): SingupResponse {
        val response = SingupResponse()

        val result = transaction {
            addLogger(StdOutSqlLogger)

            Users.select {
                Users.username eq request.username
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

                Users.insert {
                    it[username] = request.username
                    it[fullname] = request.fullname
                    it[phone] = request.phone
                    it[password] = request.password
                }
            }
            response.success = true
            response.message = "สมัครสำเร็จ"
        }
        return response


    }



    fun profile(userId: Int): ProfileModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            Users.slice(Users.username,Users.fullname,Users.phone)
                .select { Users.user_id eq userId }
                .map { ProfileMap.toProfile(it) }
                .single()
        }

    }
    fun repair(req:RepairRequest):RepairResponse{
        val response =RepairResponse()
        val statement =transaction {
            addLogger(StdOutSqlLogger)
            Orderl.insert {
                it[user_id]=req.userid.toString().toInt()
                it[abode]=req.abode.toString()
                it[repair_list]=req.repair_list.toString()
//                it[dateLong]=req.date.toString().toLong()
                it[latitude]=req.latitudeval.toString().toDouble()
                it[longitude]=req.longitude.toString().toDouble()
                it[id_technician]=0
                it[type_job]=req.idtypejob.toString().toInt()

            }
        }
        val result = statement.resultedValues?.size == 1
        response.success = result
        response.message = "Insert success"
        return response
    }
     fun history(req:HistoryRequest):List<HistoryModel>{


           return transaction {
             addLogger(StdOutSqlLogger)
             Orderl.slice(
                 Orderl.abode,
                 Orderl.order_id,
                 Orderl.repair_list,
                 Orderl.dateLong
             )
                 .select{Orderl.user_id eq req.id}
                 .andWhere { Orderl.dateLong .between(req.star,req.end) }
                 .map { HistoryMap.toHistory(it) }

         }


     }
    fun orderlall():List<OrderModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            Orderl.selectAll()
                .map { HistoryMap.toOrder(it)}

        }
    }

    fun HistoryDetail(req:HistoryDetailRequest):List<HistoryDetailModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            Orderl.slice(
                Orderl.order_id,
                Orderl.date,
                Orderl.dateLong,
                Orderl.repair_list,
                Orderl.abode,
                Orderl.price
            )
                .select{Orderl.user_id eq req.id}
//                .andWhere { Orderl.dateLong eq req.date }
                .map { HistoryMap.toOrderdetail(it) }
        }

    }
    fun Selettypejob():List<SeletTypejobModel>{
        return transaction {
            addLogger(StdOutSqlLogger)
            Type_technician.selectAll()
                .map { SeletTypejobMap.toSeletTypejob(it) }
        }
    }

}