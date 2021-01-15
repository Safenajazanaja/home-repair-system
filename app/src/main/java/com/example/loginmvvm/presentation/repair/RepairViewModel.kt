package com.example.loginmvvm.presentation.repair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.RepairModel
import com.example.loginmvvm.data.request.RepairRequest

class RepairViewModel: ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
    get() = _toast

    private var _repair = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _repair

    fun repair(request:RepairRequest) {
        when {
            request.abode.isBlank() -> _toast.value = "กรุณากรอกที่อยู่"
            request.repair_list.isBlank() -> _toast.value = "กรุรากรอกงานที่ต้องการซ่อม"
//            request.date==null  -> _toast.value = "กรุณาเลือกวันที่"

            else -> {

                val result = DataSource.repair(request)
                _repair.value=result.success


            }

        }

    }
}
