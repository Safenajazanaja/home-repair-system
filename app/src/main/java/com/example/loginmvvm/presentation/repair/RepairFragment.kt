package com.example.loginmvvm.presentation.repair


import android.app.DatePickerDialog

import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.data.request.RepairRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.frament_call.*
import java.util.*


class RepairFragment : BaseFragment(R.layout.frament_call),OnMapReadyCallback{

    private lateinit var viewModel: RepairViewModel
    private lateinit var map: GoogleMap


    //Calendar
    private var mCalendar: Calendar? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = context?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)
        viewModel = ViewModelProvider(this).get(RepairViewModel::class.java)
//        viewModel.repair()
        viewModel.toast.observe(this, { str ->
            Toast.makeText(context, "$str", Toast.LENGTH_SHORT).show()
        })
        btCalendar.setOnClickListener {
            val calendar = mCalendar ?: Calendar.getInstance()

            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]

            calendar.add(Calendar.DATE,0)
           val dateDialog= DatePickerDialog(
                requireContext(),
                { view, year, month, dayOfMonth ->
                    Toast.makeText(
                        context,
                        "$year/${month.plus(1)}/$dayOfMonth",
                        Toast.LENGTH_SHORT
                    ).show()

                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    mCalendar = calendar

                },
                year,
                month,
                day,
            )
            calendar.add(Calendar.DATE,0)
            dateDialog.datePicker.minDate=calendar.timeInMillis
//                .show()
            dateDialog.show()

        }
        Bt_ok.setOnClickListener {
            val Abode=re_abode.text.toString()
            val RepairList=re_joblist.text.toString()
            val Repair=RepairRequest(userId,Abode,RepairList,mCalendar?.timeInMillis)
            viewModel.repair(Repair)

//            Toast.makeText(context, "${mCalendar?.timeInMillis}", Toast.LENGTH_SHORT).show()
        }



    }


    override fun onMapReady(googleMap: GoogleMap) {
        map=googleMap
        val  sydney=LatLng(-34.0,151.0)
        val zoomLevel=15f
        map.addMarker(MarkerOptions().position(sydney).title("testfds"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomLevel))

    }

}
