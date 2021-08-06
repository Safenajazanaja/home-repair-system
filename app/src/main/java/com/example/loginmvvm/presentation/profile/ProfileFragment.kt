package com.example.loginmvvm.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.base.Dru
import com.example.loginmvvm.base.Dru.loadImageCircle
import com.example.loginmvvm.base.onItemSelected
import com.example.loginmvvm.data.models.ProvincesModel
import com.example.loginmvvm.data.models.SeletTypejobModel
import com.example.loginmvvm.data.models.TimeJobModel
import com.example.loginmvvm.data.request.HomeRequest
import com.example.loginmvvm.data.request.ImagsRequest
import com.example.loginmvvm.presentation.repair.engineer.SpinnertimeAdapyer
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.android.synthetic.main.fragment_call.bar_spinner_datejob
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.home_dialog.*
import kotlinx.android.synthetic.main.home_dialog.view.*
import java.util.*


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private var mImageUri: Uri? = null

    private lateinit var type: ProvincesModel
    private var name: String? = null
    private var id: Int? = null


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

        val idprovinces:Int?=null

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.profile(userId)


        viewModel.profileModel.observe(this, { profile ->
            tv_username.text = profile.name.toString()
            tv_full_name.text = profile?.name.toString()
            tv_phone.text = profile?.telephone.toString()
            tv_home.text = profile.abode.toString()
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
            val mDialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.home_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("แก้ไขที่อยู่")
            //show dialog
            val mAlertDialog = mBuilder.show()
//            viewModel.provinces.observe(this, { provinces ->
//                val pos=provinces

                mAlertDialog.bar_spinner_provinces.adapter = SpinnerprovincesAdapyer(
                    requireContext(),
                    viewModel.provinces.value as MutableList<ProvincesModel>
                )
                mAlertDialog.bar_spinner_provinces.onItemSelected<ProvincesModel> {
                    type = it
                    id = it.id
                    name = it.name


                }
            val provincesId = (viewModel.profileModel.value?.id_provinces?:1)-1
                mAlertDialog.bar_spinner_provinces.setSelection(provincesId)
//            })


            //login button click of custom layout
            mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val home = mDialogView.dialog_text.text.toString()

                //set the input text in TextView
                tv_home.setText("$home")
                val req = HomeRequest(
                    id = userId,
                    home = home
                )
                viewModel.uphome(req)
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }


        }


        iv_photo.setOnClickListener {
            Log.d(TAG, "onActivityCreated: ")
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 123)
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


//    private fun setSpinnerprovinces() {
//
//        viewModel.provinces.observe(this,{provinces ->
//            bar_spinner_provinces.adapter=SpinnerprovincesAdapyer(
//                requireContext(),
//                provinces as MutableList<ProvincesModel>
//            )
//            bar_spinner_provinces.onItemSelected<ProvincesModel> {
//                type=it
//                id=it.id
//                name=it.name
//            }
//
//        })
//
//
//    }

    companion object {
        private const val TAG = "MainActivity"
    }


}