package com.example.loginmvvm.presentation.history.detail

import android.util.Log
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.SimpleRecyclerView
import com.example.loginmvvm.data.models.ListModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_listitem.view.*


class ListAdapter:SimpleRecyclerView<ListModel>() {
    override fun getLayout(): Int = R.layout.item_listitem
    override fun View.onBindViewHolder(currentData: ListModel, beforeData: ListModel?) {
        val price = currentData.qty
        val Unitprice=currentData.Unitprice
        tv_name.text=currentData.name
        tv_qty.text=currentData.qty.toString()
        tv_Unitprice.text=currentData.Unitprice.toString()
        tv_price.text= (price!! * Unitprice!!).toString()

        Log.d(TAG, "repair2: ${Gson().toJson(currentData)}")
    }

    companion object {
        private const val TAG = "adp"
    }
}