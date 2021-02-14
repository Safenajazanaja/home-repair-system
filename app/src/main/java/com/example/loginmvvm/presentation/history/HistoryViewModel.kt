package com.example.loginmvvm.presentation.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.HistoryModel
import com.example.loginmvvm.data.request.HistoryRequest
import com.google.gson.Gson
import java.text.SimpleDateFormat


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

    private var _history2 = MutableLiveData<List<HistoryModel2>>()
    val history2: LiveData<List<HistoryModel2>>
        get() = _history2

    fun repair(request: HistoryRequest) {
        when {
            request.star == null -> _toast.value = "กรุณาเลือกวันที่เริ่มต้น"
            request.end == null -> _toast.value = "กรุณาเลือกวันที่สิ้นสุด"


            else -> {
                // list one
                val result = DataSource.history(request)
                _history.value = result

                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val result2 = result
//                    .sortedBy { sdf.format(it.date) } //ORDER BY
                    .distinctBy { sdf.format(it.date) } // group by

                    .map { db ->
                        HistoryModel2(
//                        order = db.order,
//                        adode = db.adode,
//                        repair_List = db.repair_List,
                            date = sdf.format(db.date),
                            sumOrderByDate = result.filter { sdf.format(it.date) == sdf.format(db.date) }
                                .count(),
                            orders = result.filter { sdf.format(it.date) == sdf.format(db.date) }
                                .map {
                                    OrderModel2(
                                        order = it.order,
                                        adode = it.adode,
                                        repair_List = it.repair_List,
                                        date = sdf.format(it.date),
                                    )
                                }
                        )
                    }.sortedBy { it.date }

                Log.d(TAG, "repair: ${Gson().toJson(result2)}")

                _history2.value = result2

            }

        }

    }

    companion object {
        private const val TAG = "HistoryViewModel"
    }

}

data class HistoryModel2(
//    val order: Int? = null,
//    val adode: String? = null,
//    val repair_List: String? = null,
    val date: String? = null,
    val sumOrderByDate: Int,
    val orders: List<OrderModel2>
)

data class OrderModel2(
    val order: Int? = null,
    val adode: String? = null,
    val repair_List: String? = null,
    val date: String? = null
)
