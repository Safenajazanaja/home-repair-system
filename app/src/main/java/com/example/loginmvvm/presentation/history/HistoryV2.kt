package com.example.loginmvvm.presentation.history

import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.SimpleExpandableListAdapter
import com.example.loginmvvm.data.models.HistoryModel2
import com.example.loginmvvm.data.models.OrderModeldetail
import kotlinx.android.synthetic.main.item_history_date.view.*
import kotlinx.android.synthetic.main.item_single_item_main.view.*

class HistoryV2:SimpleExpandableListAdapter<HistoryModel2, OrderModeldetail>() {
    override fun getPropertyDetailList(item: HistoryModel2): List<OrderModeldetail> {
        return item.orders
    }

    override fun onCreateViewHolderMain(): Int = R.layout.item_single_item_main

    override fun View.onBindViewHolderMain(item: HistoryModel2) {
        tvDate.text = item.date
    }

    override fun onCreateViewHolderDetail(): Int = R.layout.item_history_date

    override fun View.onBindViewHolderDetail(item: OrderModeldetail) {
        tv_repair_list.text=item.repair_List
    }
}