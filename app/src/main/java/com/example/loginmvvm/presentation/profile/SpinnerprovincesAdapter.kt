package com.example.loginmvvm.presentation.profile

import android.content.Context
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseSpinner
import com.example.loginmvvm.data.models.ProvincesModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*

class SpinnerprovincesAdapter(context: Context, list: MutableList<ProvincesModel>):BaseSpinner<ProvincesModel>(context,list) {
    override fun getLayout(): Int = R.layout.item_spinner_base

    override fun View.onBindViewHolder(data: ProvincesModel) {
      tvSpinnerBase.text=data.name
    }
}