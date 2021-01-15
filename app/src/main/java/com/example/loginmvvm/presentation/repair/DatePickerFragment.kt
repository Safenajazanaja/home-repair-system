package com.example.loginmvvm.presentation.repair

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.frament_call.*
import java.util.*

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(activity!!, activity as DatePickerDialog.OnDateSetListener?, year, month, day)
        datePickerDialog.datePicker.minDate = c.timeInMillis
        return datePickerDialog

        //return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
    }
}

