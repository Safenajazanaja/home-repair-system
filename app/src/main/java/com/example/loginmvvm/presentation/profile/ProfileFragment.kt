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
import com.example.loginmvvm.data.request.HomeRequest
import com.example.loginmvvm.data.request.ImagsRequest
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.home_dialog.view.*
import java.util.*


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private var mImageUri: Uri? = null

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
            tv_home.text=profile.abode.toString()

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
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("แก้ไขที่อยู่")
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val home = mDialogView.dialog_text.text.toString()

                //set the input text in TextView
                tv_home.setText("$home")
                val  req= HomeRequest(
                    id=userId,
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

                Toast.makeText(requireContext(), "${it?.response}", Toast.LENGTH_SHORT).show()
            }


        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }


}