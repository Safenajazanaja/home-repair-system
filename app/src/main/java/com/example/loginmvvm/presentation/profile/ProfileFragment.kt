package com.example.loginmvvm.presentation.profile

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.data.response.LoginResponse
import com.example.loginmvvm.presentation.login.LoginViewModel

import com.srisuk.computerrepair.ui.BaseFragment
import kotlinx.android.synthetic.main.frament_profile.*

class ProfileFragment:BaseFragment(R.layout.frament_profile) {
    private lateinit var viewModel: ProfileViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId =LoginResponse()
        val id=userId.userId

//        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
//        viewModel.profile(userId)

        Log.d(ContentValues.TAG, "onActivityCreated:$id ")
        val profile= id?.let { dataSource.profile(it) }
        tv_username.text=profile?.username.toString()
        tv_full_name.text=profile?.name.toString()
        tv_phone.text=profile?.telephone.toString()


    }


}