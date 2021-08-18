package com.example.loginmvvm.presentation.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.*
import com.example.loginmvvm.data.request.RepairRequest

class RepairViewModel : ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _repair = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _repair


    // RESPONSE
    private var _typejob = MutableLiveData<List<SeletTypejobModel>>()
    val typejob: LiveData<List<SeletTypejobModel>>
        get() = _typejob


    private var _timejob = MutableLiveData<List<TimeJobModel>>()
    val timejob: LiveData<List<TimeJobModel>>
        get() = _timejob

    private var _abode = MutableLiveData<AbodeModel>()
    val abode: LiveData<AbodeModel>
        get() = _abode


    private val _provinces = MutableLiveData<List<ProvincesModel>>()
    val provinces: LiveData<List<ProvincesModel>>
        get() = _provinces


    private val _amphur = MutableLiveData<List<AmphurModel>>()
    val amphur: LiveData<List<AmphurModel>>
        get() = _amphur

    private val _district = MutableLiveData<List<DistrictModel>>()
    val district: LiveData<List<DistrictModel>>
        get() = _district


    private val _profileModel = MutableLiveData<ProfileModel>()
    val profileModel: LiveData<ProfileModel>
        get() = _profileModel

    // REQUEST

    fun profile(userId: Int?) {
        userId?.let {
            _profileModel.value = DataSource.profile(it)
        }


    }
    fun repair(request: RepairRequest) {
        when {
            request.abode.isBlank() -> _toast.value = "กรุณากรอกที่อยู่"
            request.repair_list.isBlank() -> _toast.value = "กรุรากรอกงานที่ต้องการซ่อม"
            request.date == null -> _toast.value = "กรุณาเลือกวันที่ต้องการ"

//            else -> {
//                val result = DataSource.repair(request)
//                _repair.value = result.success
//
//
//            }

        }

    }

    fun amphurselect(amphurid:Int){
        _amphur.value=DataSource.amphurselect(amphurid)

    }
    fun districtselet(districtid:Int){
        _district.value=DataSource.districtselect(districtid)
    }

    fun settextabode(id: Int) {
        _abode.value = DataSource.abodesettext(id)
    }

    fun confim(request: RepairRequest) {
        val result = DataSource.repair(request)
        _repair.value = result.success
    }
//    fun seletjob(request:RepairRequest?=null){
//
//        val a =DataSource.Selettypejob()
//        _typejob.value=a
//    }

    init {
//        seletjob()
        _typejob.value = DataSource.Selettypejob()
        _timejob.value = DataSource.Timejob()
        _provinces.value = DataSource.provinces()
        _amphur.value = DataSource.amphur()
        _district.value = DataSource.district()


    }

}
