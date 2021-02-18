package com.example.loginmvvm.presentation.history

import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.data.models.HistoryDetailModel
import com.example.loginmvvm.data.models.HistoryModel2
import com.example.loginmvvm.data.models.OrderModeldetail
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_history_date.view.*

class DetailHistoryAdapter: BaseRecyclerView<HistoryDetailModel>() {
    override fun getLayout(): Int = R.layout.item_history_date

    override fun View.onBindViewHolder(data: HistoryDetailModel) {
        var price:Int?=data.price
        tv_repair_list.text=data.repair_List
        tv_adode_date.text=data.adode
        if (price==null){
            tv_price.text= "อยู่ระหว่างการประเมิน"
        }else{
            tv_price.text=price.toString()
        }


    }
}