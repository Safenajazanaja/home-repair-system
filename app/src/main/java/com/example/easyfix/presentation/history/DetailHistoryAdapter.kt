package com.example.easyfix.presentation.history

import android.view.View
import com.example.easyfix.R
import com.example.easyfix.data.models.HistoryDetailModel
import com.srisuk.computerrepair.ui.BaseRecyclerView
import kotlinx.android.synthetic.main.item_history_date.view.*

class DetailHistoryAdapter: BaseRecyclerView<HistoryDetailModel>() {
    override fun getLayout(): Int = R.layout.item_history_date

    override fun View.onBindViewHolder(data: HistoryDetailModel) {
        var price:Int?=data.price
        tv_repair_list.text="รายการที่ซ่อม "+" "+data.repair_List
        tv_adode_date.text="ที่อยู่ "+" "+data.adode
        if (price==null){
            tv_price.text= "ราคา "+" "+"อยู่ระหว่างการประเมิน"
        }else{
            tv_price.text="ราคา "+" "+price.toString()
        }


    }
}