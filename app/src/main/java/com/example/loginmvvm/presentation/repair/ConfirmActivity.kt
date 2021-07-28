package com.example.loginmvvm.presentation.repair

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.data.request.RepairRequest
import com.example.loginmvvm.presentation.main.MainActivity
import com.example.loginmvvm.presentation.main.MainViewModel
import com.example.loginmvvm.presentation.profile.ProfileFragment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class ConfirmActivity : BaseActivity(), OnMapReadyCallback {
    private lateinit var viewModel: RepairViewModel
    private var mGoogleMap: GoogleMap? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mMarker: Marker? = null
    private var latitudeMap: Double = 0.0
    private var longitudeMap: Double = 0.0
    private lateinit var viewModel2: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        val userId = intent.getIntExtra("user_id", 0)
        val abode = intent.getStringExtra("abode")
        val repair_list = intent.getStringExtra("repair_list")
        val date = intent.getLongExtra("date", 0)
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val idtypejob = intent.getIntExtra("type_job", 0)
        val idtime=intent.getIntExtra("timejob",0)
        val timezone=intent.getStringExtra("timezone")

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(date)
        viewModel = ViewModelProvider(this).get(RepairViewModel::class.java)
        latitudeMap = latitude.toDouble()
        longitudeMap = longitude.toDouble()

        tv_namejob_confirm.text = repair_list.toString()
        tv_date_confirm.text = dateString.toString()
        tv_abode_confirm.text = abode.toString()
        tv_time_confirm.text=timezone.toString()

        bt_cancel_confirm.setOnClickListener {
//            val intent = Intent(baseContext, MainActivity::class.java).putExtra("id", userId)
//            startActivity(intent)
            fragment_container

        }

        bt_confirm_confirm.setOnClickListener {
            val Repair = RepairRequest(
                userid = userId,
                abode = abode.toString(),
                repair_list = repair_list.toString(),
                latitudeval = latitude.toDouble(),
                longitude = longitude.toDouble(),
                idtypejob = idtypejob,
                date = date,
                idtime = idtime,
                timezone = timezone
            )
            viewModel.confim(Repair)
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        val latLng = LatLng(latitudeMap, longitudeMap)
        mGoogleMap?.addMarker(MarkerOptions().position(latLng).title("บ้าน"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

    }

}