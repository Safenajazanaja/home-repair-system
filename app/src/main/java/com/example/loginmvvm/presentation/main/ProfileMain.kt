package com.example.loginmvvm.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.presentation.history.HistoryFragment
import com.example.loginmvvm.presentation.profile.ProfileFragment
import com.example.loginmvvm.presentation.repair.RepairFragment
import com.example.loginmvvm.presentation.trace.TraceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class ProfileMain: BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
//        val userId=intent.getIntExtra("id",0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigationv.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null){
            replaceFragment(ProfileFragment())
        }


    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            fragment
        ).commit()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_profile -> ProfileFragment()
                R.id.nav_home -> RepairFragment()
                R.id.nav_history -> HistoryFragment()
                R.id.nav_trace -> TraceFragment()
                else -> ProfileFragment()
            }
            replaceFragment(selectedFragment)
            true
        }
}
