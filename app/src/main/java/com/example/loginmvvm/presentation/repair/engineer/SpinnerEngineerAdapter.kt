package com.example.loginmvvm.presentation.repair.engineer

import android.content.Context
import android.view.View
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseSpinner
import com.example.loginmvvm.data.models.EngineerSeletModel
import kotlinx.android.synthetic.main.item_spinner_base.view.*
import java.security.AccessControlContext

class SpinnerEngineerAdapter(
    context:Context,
    list: MutableList<EngineerSeletModel>
) : BaseSpinner<EngineerSeletModel>(context,list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: EngineerSeletModel) {
        tvSpinnerBase.text="ช่าง"+data.fullname
    }
}