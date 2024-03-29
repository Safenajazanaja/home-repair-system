package com.example.loginmvvm.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.base.Dru
import com.example.loginmvvm.base.Dru.loadImageCircle
import com.example.loginmvvm.base.onItemSelected
import com.example.loginmvvm.data.models.AmphurModel
import com.example.loginmvvm.data.models.DistrictModel
import com.example.loginmvvm.data.models.ProvincesModel
import com.example.loginmvvm.data.request.HomeRequest
import com.example.loginmvvm.data.request.ImagsRequest
import com.example.loginmvvm.presentation.main.ProfileMain
import com.example.loginmvvm.utils.awaitLastLocation
import com.example.loginmvvm.utils.getGoogleMap
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.home_dialog.*
import kotlinx.android.synthetic.main.home_dialog.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private var mImageUri: Uri? = null

    private lateinit var type: ProvincesModel
    private var name: String? = null
    private var id: Int? = null

    private lateinit var type2: AmphurModel
    private var name2: String? = null
    private var id2: Int? = null

    private lateinit var type3: DistrictModel
    private var name3: String? = null
    private var id3: Int? = null

    private var mMarker: Marker? = null
    private var latitude: Double? = 0.0
    private var longitude: Double? = 0.0


    //    private var mImageUrl: Url? = null
    private lateinit var viewModel: ProfileViewModel
    private var user: Int? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userId = context?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)
        user = userId


        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.profile(userId)


        viewModel.profileModel.observe(this, { profile ->
            tv_username.text = profile.name.toString()
            tv_full_name.text = profile?.name.toString()
            tv_phone.text = profile?.telephone.toString()
            tv_home.text = profile.abode.toString() + "\nต. " + profile.district_name.toString()+ "\nอ. " + profile.amphur_name.toString() + "\nจ. " +profile.province_name.toString()
//            profile.id_provinces=idprovinces

            if (profile.img == null) {
                iv_photo.setImageResource(R.drawable.ic_user)
            } else if (profile.img != null) {
                val baseUrl = profile.img.toString()
                iv_photo.loadImageCircle(baseUrl)
            }
        })


        edithomeButton.setOnClickListener {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.home_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialogView).setTitle("แก้ไขที่อยู่")
            //show dialog
            val mAlertDialog = mBuilder.show()
//            viewModel.provinces.observe(this, { provinces ->
//                val pos=provinces
            mAlertDialog.bar_spinner_provinces.adapter = SpinnerprovincesAdapter(
                requireContext(),
                viewModel.provinces.value as MutableList<ProvincesModel>
            )
            mAlertDialog.bar_spinner_provinces.onItemSelected<ProvincesModel> {
                type = it
                id = it.id
                name = it.name
                viewModel.amphurselect(it.id)
                mAlertDialog.bar_spinner_amphur.adapter = SpinneramphurAdapter(
                    requireContext(),
                    viewModel.amphur.value as MutableList<AmphurModel>
                )
                mAlertDialog.bar_spinner_amphur.onItemSelected<AmphurModel> {
                    type2 = it
                    id2 = it.id
                    name2 = it.name

                    viewModel.districtselet(it.id)
                    mAlertDialog.bar_spinner_districts.adapter = SpinnerdistrictAdapter(
                        requireContext(),
                        viewModel.district.value as MutableList<DistrictModel>
                    )
                    mAlertDialog.bar_spinner_districts.onItemSelected<DistrictModel> {
                        type3 = it
                        id3 = it.id
                        name3 = it.name
                    }
                }
            }

            mAlertDialog.bar_spinner_amphur.adapter = SpinneramphurAdapter(
                requireContext(),
                viewModel.amphur.value as MutableList<AmphurModel>
            )
            mAlertDialog.bar_spinner_amphur.onItemSelected<AmphurModel> {
                type2 = it
                id2 = it.id
                name2 = it.name
            }

            mAlertDialog.bar_spinner_districts.adapter = SpinnerdistrictAdapter(
                requireContext(),
                viewModel.district.value as MutableList<DistrictModel>

            )
            mAlertDialog.bar_spinner_districts.onItemSelected<DistrictModel> {
                type3 = it
                id3 = it.id
                name3 = it.name
            }
            val amphurId = (viewModel.profileModel.value?.amphur_id ?: 1) - 1
            val provincesId = (viewModel.profileModel.value?.province_id ?: 1) - 1


            val districtId = (viewModel.profileModel.value?.district_id ?: 1) - 1

            Log.d(TAG, "chekImg: {$districtId}")

            mAlertDialog.bar_spinner_provinces.setSelection(provincesId)
            mAlertDialog.bar_spinner_amphur.setSelection(amphurId)
            mAlertDialog.bar_spinner_districts.setSelection(districtId)
//            })


            //login button click of custom layout
            mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog
//                mAlertDialog.dismiss()


                //get text from EditTexts of custom layout
                val home = mDialogView.dialog_text.text.toString()

                //set the input text in TextView
                tv_home.setText("$home")
                val req = HomeRequest(
                    id = userId,
                    home = home,
                    amphurId = type2.id,
                    provincesId = type.id,
                    districtId = type3.id
                )
                viewModel.uphome(req)

                val intent =
                    Intent(requireContext(), ProfileMain::class.java).putExtra("id", userId)
                startActivity(intent)
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                //dismiss dialog
//                mAlertDialog.dismiss()
                val intent =
                    Intent(requireContext(), ProfileMain::class.java).putExtra("id", userId)
                startActivity(intent)


            }


        }


        iv_photo.setOnClickListener {
            Log.d(TAG, "onActivityCreated: ")
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 123)
        }
        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.maps_profile) as SupportMapFragment?

        MainScope().launch {
            val locationProviderClient = LocationServices
                .getFusedLocationProviderClient(requireActivity())
            val googleMap = mapFragment?.getGoogleMap()

            val location = locationProviderClient.awaitLastLocation()

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
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude),
                15f
            )
            googleMap?.animateCamera(cameraUpdate)
            googleMap?.isMyLocationEnabled = true
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            mImageUri = data.data
            iv_photo.loadImageCircle(mImageUri.toString())
            val imageName = UUID.randomUUID().toString().replace("-", "") + ".jpg"
            val baseUrl = "https://easyfix204.000webhostapp.com/image/"
            val imagePath = baseUrl + imageName
            Log.d(TAG, "onCreate: ${imagePath}")
            Dru.uploadImage(requireContext(), baseUrl, imageName, mImageUri) {
                // update url
                val upimags = user?.let { ImagsRequest(it, imagePath) }

                if (upimags != null) {
                    Log.d(TAG, "onActivityResult: $upimags")
                    viewModel.chekImg(upimags)
                }
//                Toast.makeText(requireContext(), "${it?.response}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }


}