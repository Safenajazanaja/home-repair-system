package com.example.loginmvvm.presentation.repair.engineer

import android.content.Context
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseSpinner
import com.example.loginmvvm.data.models.TimeJobModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*

class SpinnertimeAdapyer(context: Context,list: MutableList<TimeJobModel>):BaseSpinner<TimeJobModel>(context,list) {
    override fun getLayout(): Int = R.layout.item_spinner_base

    override fun View.onBindViewHolder(data: TimeJobModel) {
      tvSpinnerBase.text=data.time
    }
}