package com.example.easyfix.presentation.repair.engineer

import android.content.Context
import android.view.View
import com.example.easyfix.R
import com.example.easyfix.base.BaseSpinner
import com.example.easyfix.data.models.SeletTypejobModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*

class SpinnerTypejobAdapter(context: Context, list: MutableList<SeletTypejobModel>):BaseSpinner<SeletTypejobModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: SeletTypejobModel) {
        tvSpinnerBase.text="งาน"+data.type
    }
}