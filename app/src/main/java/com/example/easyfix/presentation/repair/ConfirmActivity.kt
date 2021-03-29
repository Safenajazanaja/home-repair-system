package com.example.easyfix.presentation.repair

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.easyfix.R
import com.example.easyfix.base.BaseActivity
import com.example.easyfix.data.request.RepairRequest
import com.example.easyfix.presentation.main.MainActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_confirm.*
import java.text.SimpleDateFormat


class ConfirmActivity : BaseActivity(),OnMapReadyCallback {
    private lateinit var viewModel: RepairViewModel
    private var mMarker: Marker? = null
    private var mGoogleMap: GoogleMap? = null
    private var latitudeMap:Double = 0.0
    private var longitudeMap:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        val userId = intent.getIntExtra("user_id", 0)
        val abode = intent.getStringExtra("abode")
        val repair_list = intent.getStringExtra("repair_list")
//        val date = intent.getLongExtra("date", 0)
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val idtypejob= intent.getIntExtra("type_job", 0)
        val typename=intent.getStringExtra("type_name")
        viewModel = ViewModelProvider(this).get(RepairViewModel::class.java)

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val dateString = simpleDateFormat.format(date)

        latitudeMap=latitude.toDouble()
        longitudeMap=longitude.toDouble()
        tv_type_jobconfirm.text=typename.toString()
        tv_namejob_confirm.text=repair_list.toString()
//        tv_date_confirm.text=dateString.toString()
        tv_abode_confirm.text=abode.toString()

        bt_confirm_confirm.setOnClickListener {
            val Repair = RepairRequest(
                userid = userId,
                abode = abode.toString(),
                repair_list = repair_list.toString(),
//                date = mCalendar?.timeInMillis,
                latitudeval = latitude,
                longitude = longitude,
                idtypejob = idtypejob
            )
            viewModel.confim(Repair)
            val intent= Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        bt_cancel_confirm.setOnClickListener {
            val intent= Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps_confirm) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        val sydney = LatLng(latitudeMap, longitudeMap)
        mGoogleMap?.addMarker(MarkerOptions().position(sydney).title("บ้าน"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))

    }

}