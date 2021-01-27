package com.example.loginmvvm.presentation.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import kotlinx.android.synthetic.main.frament_history.*
import java.util.*

class HistoryFragment: BaseFragment(R.layout.frament_history) {
    private var mCalendar: Calendar? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val  userId=context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("id",0)




    }
}