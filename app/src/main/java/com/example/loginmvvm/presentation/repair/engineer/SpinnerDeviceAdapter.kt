package com.example.loginmvvm.presentation.repair.engineer

import android.content.Context
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseSpinner
import com.example.loginmvvm.data.models.SeletTypejobModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*

class SpinnertypeAdapter(context: Context,list: MutableList<SeletTypejobModel>):BaseSpinner<SeletTypejobModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: SeletTypejobModel) {
        tvSpinnerBase.text=data.type
    }
}