package com.example.loginmvvm.presentation.repair


import android.app.DatePickerDialog.OnDateSetListener
import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.data.request.RepairRequest
import kotlinx.android.synthetic.main.frament_call.*
import java.text.SimpleDateFormat
import java.util.*



class RepairFragment : BaseFragment(R.layout.frament_call),OnDateSetListener {
    private lateinit var viewModel:RepairViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val  userId=context?.getSharedPreferences("file",
            AppCompatActivity.MODE_PRIVATE)?.getInt("id",0)


//        val c=Calendar.getInstance().get(Calendar.YEAR).get(Calendar.MONTH).get(Calendar.MONTH)
//        val year=c.get(Calendar.YEAR)
//        val month=c.get(Calendar.MONTH)
//        val day=cc



        viewModel = ViewModelProvider(this).get(RepairViewModel::class.java)
//        viewModel.repair()

        viewModel.toast.observe(this, { str ->
            Toast.makeText(context ,"$str", Toast.LENGTH_SHORT).show()
        })

        btCalendar.setOnClickListener {
            val datePicker: DialogFragment = DatePickerFragment()
            datePicker.show(fragmentManager,"date picker")
//            val dpd=
//                context?.let { it1 ->
//                    DatePickerDialog(it1,DatePickerDialog.OnDateSetListener { view, c ->
//                    re_date.setText(""+c)
//                    },year,month,day)
//                }
//            dpd?.show()

        }


        Bt_ok.setOnClickListener {
//            val repairlist= re_job.text.toString()
//            val redate=re_date.text.toString().toLong()
//            val reabode=re_abode.text.toString()
//            val repair= userId?.let { it1 -> RepairRequest(it1,reabode,repairlist,redate) }
        }


    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        //String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        val currentDateString = formatter.format(c.time)
//        setData = currentDateString
        re_date.text = currentDateString
//        Toast.makeText(baseContext, "" + currentDateString, Toast.LENGTH_SHORT).show()
    }


}
