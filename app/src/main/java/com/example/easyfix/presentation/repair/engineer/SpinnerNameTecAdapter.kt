package com.example.easyfix.presentation.repair.engineer

import android.content.Context
import android.view.View
import com.example.easyfix.R
import com.example.easyfix.base.BaseSpinner
import com.example.easyfix.data.models.SeletTechnicanModel
import com.example.easyfix.data.models.SeletTypejobModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*
import kotlinx.android.synthetic.main.item_spinner_nametec.view.*

class SpinnerNameTecAdapter(context: Context, list: MutableList<SeletTechnicanModel>): BaseSpinner<SeletTechnicanModel>(context,list) {
    override fun getLayout(): Int = R.layout.item_spinner_nametec
    override fun View.onBindViewHolder(data: SeletTechnicanModel) {
        tvSpinnernametec.text="ทีมช่าง"+data.nametechnican
    }
}