package com.example.easyfix.presentation.repair

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = 2015
        val month = 5
        val day = 5
        val datePickerDialog = DatePickerDialog(activity!!, activity as DatePickerDialog.OnDateSetListener?, year, month, day)
        datePickerDialog.datePicker.minDate = c.timeInMillis
        return datePickerDialog

    }
}

