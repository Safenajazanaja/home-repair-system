package com.example.loginmvvm.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.HistoryModel
import com.example.loginmvvm.data.request.HistoryRequest


class HistoryViewModel : ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _repair = MutableLiveData<Boolean>()
    val repair: LiveData<Boolean>
        get() = _repair

    private var _history = MutableLiveData<List<HistoryModel>>()
    val history: LiveData<List<HistoryModel>>
        get() = _history

    fun repair(request: HistoryRequest) {
        when {
            request.star == null -> _toast.value = "กรุณาเลือกวันที่เริ่มต้น"
            request.end == null -> _toast.value = "กรุณาเลือกวันที่สิ้นสุด"


            else -> {
                val result = DataSource.history(request)
                _history.value = result


            }

        }

    }
}