package com.example.loginmvvm.presentation.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment

class HomepageFragment : BaseFragment(R.layout.fragment_homepage) {
    private var user: Int? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userId = context?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)

        user = userId
    }

}