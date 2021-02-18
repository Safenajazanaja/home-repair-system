package com.example.loginmvvm.presentation.history

import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.data.models.HistoryModel
import com.example.loginmvvm.data.models.HistoryModel2
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import java.text.SimpleDateFormat

class HistoryAdapter: BaseRecyclerView<HistoryModel2>() {
    override fun getLayout(): Int = R.layout.item_history

    override fun View.onBindViewHolder(data: HistoryModel2) {
//        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val dateString=simpleDateFormat.format(data.date)
//        tv_order_id.text=data.order.toString()
//        tv_adode.text=data.adode.toString()
//        tv_repair_list.text=data.repair_List.toString()
        tv_datesum.text=data.sumOrderByDate.toString()
        tv_date.text=data.date.toString()
    }
}