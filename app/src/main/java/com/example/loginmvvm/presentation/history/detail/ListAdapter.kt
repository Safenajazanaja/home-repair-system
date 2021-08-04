package com.example.loginmvvm.presentation.history.detail

import android.util.Log
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.SimpleRecyclerView
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.ListModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_history_date.view.*
import kotlinx.android.synthetic.main.item_listitem.view.*
import kotlinx.android.synthetic.main.item_listitem.view.tv_price
import java.math.RoundingMode
import java.text.DecimalFormat


class ListAdapter : SimpleRecyclerView<ListModel>() {
    override fun getLayout(): Int = R.layout.item_listitem
    override fun View.onBindViewHolder(currentData: ListModel, beforeData: ListModel?) {
        val price = currentData.qty
        val Unitprice = currentData.Unitprice
//        val sum=
//        if (beforeData?.id != null) {
//            if (currentData.id != beforeData?.id) {
//
//
//            }
//        }


        val df = DecimalFormat("###,###.00")
        df.roundingMode = RoundingMode.CEILING

        tv_name.text = currentData.name
        tv_qty.text = currentData.qty.toString()
        tv_Unitprice.text = df.format(currentData.Unitprice)
        tv_price.text = df.format(price!! * Unitprice!!)


//        Log.d(TAG, "repair2: ${Gson().toJson(currentData)}")
    }

    companion object {
        private const val TAG = "adp"
    }
}