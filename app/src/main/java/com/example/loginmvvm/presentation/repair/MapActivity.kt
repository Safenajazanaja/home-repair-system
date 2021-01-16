package com.example.loginmvvm.presentation.repair

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseLocationActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapActivity : BaseLocationActivity(){
    private var mMarker: Marker? = null
    private lateinit var map: GoogleMap
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        val locationProviderClient =
        val locationProviderClient = LocationServices
            .getFusedLocationProviderClient(this)
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->

                googleMap.isMyLocationEnabled = true
                googleMap.setMinZoomPreference(10F)
                googleMap.setMaxZoomPreference(14F)

//                val location = locationProviderClient.awaitLastLocation()
//             location=Location

                val latLng = LatLng(12.0, 25.0)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14F)
                googleMap.animateCamera(cameraUpdate)
                setMarkerChooseMap(latLng, googleMap)
//                setMapLongClick(map)




        }
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapClickListener {latLng ->
            val snippets= String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("test")
                    .snippet(snippets)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    private fun setMarkerChooseMap(latLng: LatLng, googleMap: GoogleMap) {
        mMarker?.remove()
        val marker = MarkerOptions().apply {
            position(latLng)
            icon(BitmapDescriptorFactory.defaultMarker())
        }
        mMarker = googleMap.addMarker(marker)
    }
}