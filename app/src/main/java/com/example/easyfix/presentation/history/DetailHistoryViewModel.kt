package com.example.easyfix.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfix.data.datasource.DataSource
import com.example.easyfix.data.models.HistoryDetailModel
import com.example.easyfix.data.request.HistoryDetailRequest
import java.text.SimpleDateFormat

class DetailHistoryViewModel : ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _historysum = MutableLiveData<List<HistoryDetailModel>>()
    val historysum: LiveData<List<HistoryDetailModel>>
        get() = _historysum

    fun historydetail(req: HistoryDetailRequest) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val result = DataSource.HistoryDetail(req).filter {
            sdf.format(it.date) == sdf.format(req.date)
        }
//        val sdf = SimpleDateFormat("dd/MM/yyyy")
        _historysum.value = result

    }

}