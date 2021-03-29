package com.example.easyfix.presentation.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfix.data.datasource.DataSource
import com.example.easyfix.data.models.SeletTechnicanModel
import com.example.easyfix.data.models.SeletTypejobModel
import com.example.easyfix.data.request.RepairRequest

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

    private var _nametec = MutableLiveData<List<SeletTechnicanModel>>()
    val nametec: LiveData<List<SeletTechnicanModel>>
        get() = _nametec
    // REQUEST
    fun repair(request: RepairRequest) {
        when {
            request.abode.isBlank() -> _toast.value = "กรุณากรอกที่อยู่"
            request.repair_list.isBlank() -> _toast.value = "กรุรากรอกงานที่ต้องการซ่อม"
//            request.date==null -> _toast.value="กรุณาเลือกวันที่ต้องการ"

//            else -> {
//                val result = DataSource.repair(request)
//                _repair.value = result.success
//            }

        }

    }

    fun confim(request: RepairRequest) {
        val result = DataSource.repair(request)
        _repair.value = result.success
    }
    fun selettec(){

        val result =DataSource.Selettechnician()
        _nametec.value=result
    }
//    fun selettec(request:RepairRequest?=null){
//
//        val a =DataSource.Selettypejob()
//        _typejob.value=a
//    }

    init {
//        seletjob()
        _typejob.value = DataSource.Selettypejob()
    }

}
