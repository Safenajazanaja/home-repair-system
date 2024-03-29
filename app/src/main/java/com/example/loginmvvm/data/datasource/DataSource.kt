package com.example.loginmvvm.data.datasource


import android.util.Log
import com.example.loginmvvm.data.database.*
import com.example.loginmvvm.data.map.*
import com.example.loginmvvm.data.models.*
import com.example.loginmvvm.data.request.*
import com.example.loginmvvm.data.response.ChekpricetecResponse
import com.example.loginmvvm.data.response.LoginResponse
import com.example.loginmvvm.data.response.RepairResponse
import com.example.loginmvvm.data.response.SingupResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DataSource {

    private const val TAG = "####"

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
                    it[province_id]=request.provincesId
                    it[district_id]=request.districtId
                    it[amphur_id]=request.amphurId
                    it[abode]=request.home

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
            (Users innerJoin Province innerJoin Amphur innerJoin District)
                .slice(
                    Users.username,
                    Users.fullname,
                    Users.phone,
                    Users.image,
                    Users.abode,
//                    Users.geo_id,
//                    Geography.geo_name,
                    Users.province_id,
                    Province.province_name,
                    Users.amphur_id,
                    Amphur.amphur_name,
                    Users.district_id,
                    District.district_name,
                )
                .select { Users.user_id eq userId }
                .andWhere { Users.amphur_id eq Amphur.amphur_id }
                .andWhere { Users.district_id eq District.district_id }
                .andWhere { Users.province_id eq Province.province_id }
                .map { ProfileMap.toProfile(it) }
                .single()
        }

    }

    fun abode(req: HomeRequest) {
        return transaction {
            addLogger(StdOutSqlLogger)
            Users.update({ Users.user_id eq req.id!!.toInt() }) {
                it[Users.abode] = req.home.toString()
                it[Users.province_id] = req.provincesId!!.toInt()
                it[Users.amphur_id] = req.amphurId!!.toInt()
                it[Users.district_id] = req.districtId!!.toInt()
            }


        }

    }

    fun upimgprofile(req: ImagsRequest) {
        return transaction {
            addLogger(StdOutSqlLogger)
//            val result = Users.update({ Users.image eq req.imags }) {
//                it[user_id] = req.id
//            }

            val result = Users.update({ Users.user_id eq req.id }) {
                it[Users.image] = req.imags
            }

//             Users.select {  }
//             Users.deleteWhere { Users.user_id eq req.id }

            Log.d(TAG, "upimg: $req")
            Log.d(TAG, "upimg: $result")

        }

    }

    fun repair(req: RepairRequest): RepairResponse {
        val response = RepairResponse()
        val statement = transaction {
            addLogger(StdOutSqlLogger)
            Orderl.insert {
                it[user_id] = req.userid.toString().toInt()
                it[abode] = req.abode.toString()
                it[repair_list] = req.repair_list.toString()
                it[dateLong] = req.date.toString().toLong()
                it[latitude] = req.latitudeval.toString().toDouble()
                it[longitude] = req.longitude.toString().toDouble()
                it[id_technician] = 0
                it[type_job] = req.idtypejob.toString().toInt()
                it[status] = 1
                it[idtime] = req.idtime.toString().toInt()
                it[province_id] = req.provincesId!!.toInt()
                it[amphur_id] = req.amphurId!!.toInt()
                it[district_id] = req.districtId!!.toInt()
                it[price]=0
                it[pay_type]=0
                it[period]=0
            }
        }
        val result = statement.resultedValues?.size == 1
        response.success = result
        response.message = "Insert success"
        return response
    }

    fun history(req: HistoryRequest): List<HistoryModel> {


        return transaction {
            addLogger(StdOutSqlLogger)
            (Orderl innerJoin Status innerJoin Province innerJoin Amphur innerJoin District)
                .slice(
                    Orderl.abode,
                    Orderl.order_id,
                    Orderl.repair_list,
                    Orderl.dateLong,
                    Orderl.price, //add
                    Status.status_name,
                    Province.province_name,
                    Amphur.amphur_name,
                    District.district_name
                )
                .select { Orderl.user_id eq req.id }
                .andWhere { Orderl.dateLong.between(req.star, req.end) }
                .andWhere { Orderl.province_id eq Province.province_id }
                .andWhere { Orderl.amphur_id eq Amphur.amphur_id }
                .andWhere { Orderl.district_id eq District.district_id }
                .map { HistoryMap.toHistory(it) }
        }
    }
//    fun tracejob(id: Int): List<HistoryModel> {
//        return transaction {
//            addLogger(StdOutSqlLogger)
//            (Orderl innerJoin Status)
//                .slice(
//                    Orderl.abode,
//                    Orderl.order_id,
//                    Orderl.repair_list,
//                    Orderl.dateLong,
//                    Orderl.price, //add
//                    Status.status_name
//                )
//                .select { Orderl.user_id eq id }
//                .map { HistoryMap.toHistory(it) }
//        }
//
//
//    }

    fun Selettypejob(): List<SeletTypejobModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Type_job.selectAll()
                .map { SeletTypejobMap.toSeletTypejob(it) }
        }
    }

    fun Timejob(): List<TimeJobModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Time.selectAll()
                .map { TimeJobMap.toTimeJob(it) }
        }
    }

    fun listitem(jobid: Int): List<ListModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Orderl_detail innerJoin Material)
                .slice(
                    Material.material_id,
                    Material.material_name,
                    Material.price_material,
                    Orderl_detail.qty
                )
                .select { Orderl_detail.orderl_id eq jobid }
                .map { ListMap.tolist(it) }
        }

    }

    fun upimgpay(req: ImagsRequest) {
        return transaction {
            addLogger(StdOutSqlLogger)
            val result = Orderl.update({ Orderl.order_id eq req.id }) {
                it[image] = req.imags
                it[pay_type]=1
            }

            Log.d(TAG, "upimg: $result")

        }

    }

    fun chekImage(idjob: Int): ImagModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            Orderl.slice(Orderl.image)
                .select { Orderl.order_id eq idjob }
                .map { ImageMap.toImage(it) }
                .single()
        }

    }

    fun chekPay(idjob: Int): PayModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Orderl innerJoin Pay).slice(Pay.pay_type)
                .select { Orderl.order_id eq idjob }
                .andWhere { Orderl.pay_type eq Pay.pay_id }
                .map { PayMap.toPay(it)}
                .single()
        }

    }

    fun chekStatus(jobid: Int): StatusModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            Orderl.slice(Orderl.status)
                .select { Orderl.order_id eq jobid }
                .map { StatusMap.toStatus(it) }
                .single()
        }
    }

    fun tracejob(id: Int): List<HistoryModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Orderl innerJoin Status innerJoin Province innerJoin Amphur innerJoin District)
                .slice(
                    Orderl.abode,
                    Orderl.order_id,
                    Orderl.repair_list,
                    Orderl.dateLong,
                    Orderl.price, //add
                    Status.status_name,
                    Province.province_name,
                    Amphur.amphur_name,
                    District.district_name,
                )
                .select { Orderl.user_id eq id }
                .andWhere {Orderl.status neq 5  }
                .andWhere { Orderl.status neq 4 }
//                .andWhere { Orderl.pay_type neq 2 }
                .andWhere { Orderl.province_id eq Province.province_id }
                .andWhere { Orderl.amphur_id eq Amphur.amphur_id }
                .andWhere { Orderl.district_id eq District.district_id }
                .orderBy(Orderl.dateLong to SortOrder.ASC)
                .map { HistoryMap.toHistory(it) }
        }


    }

    fun abodesettext(userId: Int): AbodeModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            Users.slice(Users.abode)
                .select { Users.user_id eq userId }
                .map { AbodeMap.toAbode(it) }
                .single()

        }

    }

    fun provinces(): List<ProvincesModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Province
                .slice(Province.province_id, Province.province_name)
                .selectAll()
                .map { ProvincesMap.toProvinces(it) }
        }
    }


    fun amphur(): List<AmphurModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Amphur
                .slice(Amphur.amphur_id, Amphur.amphur_name)
                .selectAll()
                .map { AmphurMap.toAmphur(it) }
        }
    }


    fun amphurselect(provincesId: Int): List<AmphurModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Amphur
                .slice(Amphur.amphur_id, Amphur.amphur_name)
                .select { Amphur.province_id eq provincesId }
                .map { AmphurMap.toAmphur(it) }
        }
    }

    fun district(): List<DistrictModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            District.slice(District.district_id, District.district_name)
                .selectAll()
                .map { DistrictMap.toDistrict(it) }
        }
    }

    fun districtselect(amphurid: Int): List<DistrictModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            District
                .slice(District.district_id, District.district_name)
                .select { District.amphur_id eq amphurid }
                .map { DistrictMap.toDistrict(it) }
        }
    }


    fun chekpricetec(idjob: Int): ChekpricetecResponse {
        val response = ChekpricetecResponse()
        val result = transaction {

            Orderl.slice(Orderl.price)
                .select { Orderl.order_id eq idjob }
                .map { it[Orderl.price] }
                .single()
        }

        response.price = result
        return response
    }

    fun manage(idjob: Int): ManageModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Orderl innerJoin Time innerJoin Type_job innerJoin Status innerJoin Province innerJoin Amphur innerJoin District)
                .slice(
                    Orderl.order_id,
                    Orderl.abode,
                    Orderl.repair_list,
                    Orderl.dateLong,
                    Orderl.latitude,
                    Orderl.longitude,
                    Type_job.namejob,
                    Time.time,
                    Orderl.idtime,
                    Status.status_name,
                    Province.province_name,
                    Amphur.amphur_name,
                    District.district_name,
                )
                .select { Orderl.order_id eq idjob }
                .andWhere { Orderl.status eq Status.status_id }
                .andWhere { Orderl.province_id eq  Province.province_id }
                .andWhere { Orderl.district_id eq District.district_id }
                .andWhere { Orderl.amphur_id eq Amphur.amphur_id }
                .map { ManageMap.toManageMap(it) }
                .single()

        }
    }

//    fun login2(jobid: Int): ChekpricetecResponse {
//        val response = ChekpricetecResponse()
//        val result = transaction {
//            addLogger(StdOutSqlLogger)
//            (Orderl_detail innerJoin Material)
//                .slice(
//                    Material.material_id,
//                    Material.material_name,
//                    Material.price_material,
//                    Orderl_detail.qty
//                )
//                .select { Orderl_detail.orderl_id eq jobid }
//                .count()
//                .toInt()
//
//        }
//        if (result == 1) {
//            val result2 = transaction {
//
//                Orderl.slice(Orderl.price)
//                    .select { Orderl.order_id eq jobid }
//                    .map { it[Orderl.price] }
//                    .single()
//            }
//            response.price = result2
//
//        }
//        return response
//    }
}