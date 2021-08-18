package com.example.loginmvvm.presentation.trace


import android.view.OrientationEventListener
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.SimpleExpandableListAdapter
import com.example.loginmvvm.data.models.HistoryModel2
import com.example.loginmvvm.data.models.OrderModeldetail
import kotlinx.android.synthetic.main.item_history_date.view.*

import kotlinx.android.synthetic.main.item_listitem.view.*
import kotlinx.android.synthetic.main.item_single_item_main.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class TraceAdepter : SimpleExpandableListAdapter<HistoryModel2, OrderModeldetail>() {
    override fun getPropertyDetailList(item: HistoryModel2): List<OrderModeldetail> {
        return item.orders
    }

    override fun onCreateViewHolderMain(): Int = R.layout.item_single_item_main

    override fun View.onBindViewHolderMain(item: HistoryModel2) {
        tv_date.text = "วันที่ " + item.date
        tv_datesum.text = "รวม " + item.sumOrderByDate.toString() + " รายการ"
    }

    override fun onCreateViewHolderDetail(): Int = R.layout.item_history_date

    override fun View.onBindViewHolderDetail(item: OrderModeldetail) {
        tv_repair_list.text =  "ลักษณะงาน : "+item.repair_List
        tv_adode_date.text = "ที่อยู่ : "+item.adode +"ต."+ item.district + "อ." + item.amphur + "จ." +item.province
//        if (item.price == null) {
//            tv_price.text = "ราคา : อยู่ระหว่างการประเมิน"
//        } else {
//            val df = DecimalFormat("###,###.00")
//            df.roundingMode = RoundingMode.CEILING
//            tv_price.text =  "ราคา : "+ df.format(item.price)
//        }
        tv_ststa.text ="สถานะ : "+ item.status
        setOnClickListener {
            listener?.invoke(item)
        }

    }

    private var listener: ((OrderModeldetail) -> Unit)? = null

    fun setOnClickListener(listener: (OrderModeldetail) -> Unit) {
        this.listener = listener
    }


}