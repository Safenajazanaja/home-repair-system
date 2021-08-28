package com.example.loginmvvm.presentation.trace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.HistoryModel2
import com.example.loginmvvm.data.models.OrderModeldetail
import com.google.gson.Gson
import org.jetbrains.exposed.sql.SortOrder
import java.text.SimpleDateFormat

class TraceViewModel : ViewModel() {
    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _trace = MutableLiveData<List<HistoryModel2>>()
    val trace: LiveData<List<HistoryModel2>>
        get() = _trace


    fun tracejob(id: Int) {
        val result = DataSource.tracejob(id)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = result
//            .sortedBy { sdf.format(it.date) }
//            .distinctBy { sdf.format(it.date) }
            .map { db ->
                HistoryModel2(
                    date = sdf.format(db.date),
                    datelong = db.date,
                    sumOrderByDate = result.filter { sdf.format(it.date) == sdf.format(db.date) }
                        .count(),
                    orders = result.filter { sdf.format(it.date) == sdf.format(db.date) }
                        .map {
                            OrderModeldetail(
                                orderid = it.order,
                                adode = it.adode,
                                repair_List = it.repair_List,
                                date = sdf.format(it.date),
                                price = it.price,
                                status = it.status,
                                province = it.province,
                                amphur = it.amphur,
                                district = it.district
                            )
                        }
                )
            }.sortedBy { it.datelong}
            .distinctBy { it.date }
        Log.d(TAG,"trace: ${Gson().toJson(date)}")

        _trace.value = date
    }

    companion object{
        private  const val TAG="TraceViewModel"
    }


}