package com.example.loginmvvm.presentation.profile

import android.content.Context
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseSpinner
import com.example.loginmvvm.data.models.AmphurModel

import kotlinx.android.synthetic.main.item_spinner_base.view.*

class SpinneramphurAdapter(context: Context, list: MutableList<AmphurModel>): BaseSpinner<AmphurModel>(context,list)  {
    override fun getLayout(): Int= R.layout.item_spinner_base

    override fun View.onBindViewHolder(data: AmphurModel) {
        tvSpinnerBase.text=data.name
    }
}