package com.example.loginmvvm.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.HistoryDetailModel
import com.example.loginmvvm.data.models.OrderModeldetail
import com.example.loginmvvm.data.request.HistoryDetailRequest
import java.text.SimpleDateFormat

class DetailHistoryViewModel : ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _historysum = MutableLiveData<List<HistoryDetailModel>>()
    val historysum: LiveData<List<HistoryDetailModel>>
        get() = _historysum

    fun historydetail(req: HistoryDetailRequest) {
        val result = DataSource.HistoryDetail(req)
//        val sdf = SimpleDateFormat("dd/MM/yyyy")
        _historysum.value = result

    }

}