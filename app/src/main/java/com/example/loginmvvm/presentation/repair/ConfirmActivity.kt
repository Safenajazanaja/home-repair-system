package com.example.loginmvvm.presentation.repair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_confirm.*
import java.text.SimpleDateFormat

class ConfirmActivity : BaseActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private var latitudeMap:Double = 0.0
    private var longitudeMap:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        val userId = intent.getIntExtra("user_id", 0)
        val abode = intent.getStringExtra("abode")
        val repair_list = intent.getStringExtra("repair_list")
        val date = intent.getLongExtra("date", 0)
        val latitude = intent.getFloatExtra("latitude",0.0f)
        val longitude = intent.getFloatExtra("longitude", 0.0f)

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(date)

        latitudeMap=latitude.toDouble()
        longitudeMap=longitude.toDouble()

        tv_namejob_confirm.text=repair_list.toString()
        tv_date_confirm.text=dateString.toString()
        tv_abode_confirm.text=abode.toString()

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        val latLng=LatLng(latitudeMap,longitudeMap)
        mGoogleMap?.addMarker(MarkerOptions().position(latLng).title("บ้าน"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))

    }
}