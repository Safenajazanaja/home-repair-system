package com.example.loginmvvm.presentation.repair


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.base.onItemSelected
import com.example.loginmvvm.data.models.SeletTypejobModel
import com.example.loginmvvm.data.request.RepairRequest
import com.example.loginmvvm.presentation.repair.engineer.SpinnertypeAdapter
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.frament_call.*
import org.joda.time.DateTime
import java.util.*


class RepairFragment : BaseFragment(R.layout.frament_call), OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener, GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private lateinit var viewModel: RepairViewModel

    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mGoogleMap: GoogleMap? = null
    private var mMarker: Marker? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private lateinit var type: SeletTypejobModel
    private var name: String? = null
    private var id: Int? = null


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

            calendar.add(Calendar.DATE, 0)
            val dateDialog = DatePickerDialog(
                requireContext(),
                { view, year, month, dayOfMonth ->
                    Toast.makeText(
                        context,
                        "$year/${month.plus(1)}/$dayOfMonth",
                        Toast.LENGTH_SHORT
                    ).show()
                    re_date.setText("$dayOfMonth/${month.plus(1)}/$year")



                    val calendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)


                    }
                    mCalendar = calendar


                },
                year,
                month,
                day,
            )

            calendar.add(Calendar.DATE, 0)
            dateDialog.datePicker.minDate = DateTime.now().millis
//                .show()
            dateDialog.show()


        }

        Bt_ok.setOnClickListener {
            val Abode = re_abode.text.toString()
            val RepairList = re_joblist.text.toString()
            val Repair = RepairRequest(
                userid =userId,
                abode = Abode,
                repair_list=RepairList,
                date = mCalendar?.timeInMillis,
                latitudeval = latitude,
                longitude = longitude,
                idtypejob = id
            )
            viewModel.repair(Repair)
            val intent = Intent(context, ConfirmActivity::class.java).apply {
                putExtra("user_id", Repair?.userid)
                putExtra("abode", Repair?.abode)
                putExtra("repair_list", Repair?.repair_list)
                putExtra("date", Repair?.date)
                putExtra("latitude", Repair?.latitudeval)
                putExtra("longitude", Repair?.longitude)
                putExtra("type_job",id)
            }
            startActivity(intent)

//            Toast.makeText(context, "${mCalendar?.timeInMillis}", Toast.LENGTH_SHORT).show()
        }

        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        mGoogleApiClient = GoogleApiClient.Builder(requireActivity())
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()
        mGoogleApiClient?.connect()
        mLocationRequest = LocationRequest()
            .setInterval(2500)
            .setFastestInterval(3000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)


        re_joblist.addTextChangedListener {
            // TODO: 18/2/2564
            Log.d("TAG", "onActivityCreated: ${it.toString()}")
        }



        setSpinnertypejob()

    }

    private fun setSpinnertypejob() {
        viewModel.typejob.observe(this,{selectjob ->
//            val list=viewModel.seletjob()as MutableList<SeletTypejobModel>
            bar_spinner_typejob.adapter=SpinnertypeAdapter(requireContext(),
                selectjob as MutableList<SeletTypejobModel>
            )
            bar_spinner_typejob.onItemSelected<SeletTypejobModel> {
                type=it
                id=it.id
                name=it.type
            }
        })
//        viewModel.repair()
    //        val list= DataSource.Selettypejob()as MutableList<SeletTypejobModel>
//        bar_spinner_engineer.adapter=SpinnertypeAdapter(requireContext(),list)
//        bar_spinner_engineer.onItemSelected<SeletTypejobModel> {
//            type=it
//            id=it.id
//            name=it.type
//        }

    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient,
            mLocationRequest,
            this
        )
    }

    private fun stopLocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient?.isConnected == true) mGoogleApiClient!!.disconnect()
    }

    override fun onResume() {
        super.onResume()
        if (mGoogleApiClient?.isConnected == true) startLocationUpdate()
    }

    override fun onPause() {
        super.onPause()
        if (mGoogleApiClient?.isConnected == true) stopLocationUpdate()
    }

    override fun onLocationChanged(location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
//        if (mMarker != null) {
//            mMarker!!.remove()
//        }
//        mMarker = mGoogleMap?.addMarker(
//            MarkerOptions()
//                .position(latLng)
//                .title("Dru")
//                .snippet("Dru sp" + location.longitude + " " + location.latitude).icon(
//                    BitmapDescriptorFactory.fromResource(R.drawable.house)
//                )
//        )
        latitude = location.latitude
        longitude = location.longitude


    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        setMapLongClick(googleMap)
        googleMap.isMyLocationEnabled = true
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
    }

    private fun setMapLongClick(googleMap: GoogleMap) {
        googleMap.setOnMapClickListener { latLng ->
            val snippets = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            mMarker?.remove()
            mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            mMarker = googleMap.addMarker(MarkerOptions().position(latLng))


        }

    }

    override fun onConnected(bundle: Bundle?) {
        startLocationUpdate()
    }

    override fun onConnectionSuspended(i: Int) {
        mGoogleApiClient?.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
    override fun onMyLocationButtonClick(): Boolean {
//        Toast.makeText(requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT)
//            .show()

        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
//        Toast.makeText(requireContext(), "Current location:\n$location", Toast.LENGTH_LONG)
//            .show()
        latitude = location.latitude
        longitude = location.longitude

    }


}
