package com.example.loginmvvm.presentation.repair


import android.app.DatePickerDialog
import android.content.Intent
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
import com.example.loginmvvm.data.models.TimeJobModel
import com.example.loginmvvm.data.request.RepairRequest
import com.example.loginmvvm.presentation.repair.engineer.SpinnertimeAdapyer
import com.example.loginmvvm.presentation.repair.engineer.SpinnertypeAdapter
import com.example.loginmvvm.utils.awaitLastLocation
import com.example.loginmvvm.utils.getGoogleMap
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_call.*

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import java.util.*


@ExperimentalCoroutinesApi
class RepairFragment : BaseFragment(R.layout.fragment_call) {

    private lateinit var viewModel: RepairViewModel
    private var mMarker: Marker? = null
    private var latitude: Double? = 0.0
    private var longitude: Double? = 0.0

    private lateinit var type: SeletTypejobModel
    private var nametype: String? = null
    private var idtype: Int? = null

    private lateinit var timejobzone: TimeJobModel
    private var nametime: String? = null
    private var idtime: Int? = null


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

        userId?.let { viewModel.settextabode(it) }

        viewModel.abode.observe(this, { home ->
            re_abode.setText("${home.abode}")
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
            Log.d(TAG, "onActivityCreated: ${mCalendar?.timeInMillis}")
            val Abode = re_abode.text.toString()

            val RepairList = re_joblist.text.toString()
            val Repair = RepairRequest(
                userid = userId,
                abode = Abode,
                repair_list = RepairList,
                date = mCalendar?.timeInMillis,
                latitudeval = latitude,
                longitude = longitude,
                idtypejob = idtype,
                idtime = idtime,
                timezone = nametime
            )

            Toasty.Config.getInstance().setTextSize(30)
            when {

                Repair.abode.isBlank() -> Toasty.warning(requireContext(), "กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show()
                Repair.abode.isBlank() -> Toasty.warning(requireContext(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
                Repair.repair_list.isBlank() -> Toasty.warning(requireContext(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
                Repair.date == null -> Toasty.warning(requireContext(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()

                else ->{
                    viewModel.repair(Repair)
                    val intent = Intent(context, ConfirmActivity::class.java).apply {
                        putExtra("user_id", userId)
                        putExtra("abode", Abode)
                        putExtra("repair_list", RepairList)
                        putExtra("date", Repair?.date)
                        putExtra("latitude", latitude)
                        putExtra("longitude", longitude)
                        putExtra("type_job", idtype)
                        putExtra("timejob", idtime)
                        putExtra("timezone", nametime)
                    }
                    startActivity(intent)
                }
            }




//            Toast.makeText(context, "${mCalendar?.timeInMillis}", Toast.LENGTH_SHORT).show()
        }

        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment?

        re_joblist.addTextChangedListener {
            // TODO: 18/2/2564
            Log.d("TAG", "onActivityCreated: ${it.toString()}")
        }



        setSpinnertypejob()
        setSpinnerdatejob()

        MainScope().launch {
//            Log.d("###", "onActivityCreated: 1")
            val locationProviderClient = LocationServices
                .getFusedLocationProviderClient(requireActivity())

//            Log.d("###", "onActivityCreated: 2")
            val googleMap = mapFragment?.getGoogleMap()

//            Log.d("###", "onActivityCreated: 3")
            val location = locationProviderClient.awaitLastLocation()

//            Log.d("###", "onActivityCreated: 4")

//            if (location!=null){
            latitude = location.latitude
            longitude = location.longitude
            mMarker = googleMap?.addMarker(
                MarkerOptions().position(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                )
            )
            setMapLongClick(googleMap)

            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude),
                15f
            )
            googleMap?.animateCamera(cameraUpdate)
            googleMap?.isMyLocationEnabled = true
//            }
            // real time
//            locationProviderClient.locationFlow().collect {
//            Toast.makeText(context, "${latitude}, ${longitude}", Toast.LENGTH_SHORT).show()
//            }


        }

    }

    private fun setSpinnertypejob() {
        viewModel.typejob.observe(this, { selectjob ->
//            val list=viewModel.seletjob()as MutableList<SeletTypejobModel>
            bar_spinner_typejob.adapter = SpinnertypeAdapter(
                requireContext(),
                selectjob as MutableList<SeletTypejobModel>
            )
            bar_spinner_typejob.onItemSelected<SeletTypejobModel> {
                type = it
                idtype = it.id
                nametype = it.type
            }
        })

    }

    private fun setSpinnerdatejob() {
        viewModel.timejob.observe(this, { timejob ->
            bar_spinner_datejob.adapter = SpinnertimeAdapyer(
                requireContext(),
                timejob as MutableList<TimeJobModel>
            )

            bar_spinner_datejob.onItemSelected<TimeJobModel> {
                timejobzone = it
                idtime = it.id
                nametime = it.time

            }

        })


    }

    private fun setMapLongClick(googleMap: GoogleMap?) {
        googleMap?.setOnMapClickListener { latLng ->
            val snippets = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            mMarker?.remove()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            mMarker = googleMap.addMarker(MarkerOptions().position(latLng))


        }

    }


    companion object {
        private const val TAG = "HistoryFragment"
    }
}
