package com.example.loginmvvm.presentation.main

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.data.database.Users
import com.example.loginmvvm.data.models.ProfileModel
import com.example.loginmvvm.presentation.history.HistoryFragment
import com.example.loginmvvm.presentation.history.HistoryModel2
import com.example.loginmvvm.presentation.profile.ProfileFragment
import com.example.loginmvvm.presentation.repair.RepairFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
//        val userId=intent.getIntExtra("id",0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigationv.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null)
            replaceFragment(RepairFragment())

        ///
        val data = """
            [
              {
                "date": "29/01/2021",
                "orders": [
                  {
                    "adode": "asas",
                    "date": "29/01/2021",
                    "order": 27,
                    "repair_List": "sasas"
                  },
                  {
                    "adode": "asas",
                    "date": "29/01/2021",
                    "order": 28,
                    "repair_List": "sasas"
                  },
                  {
                    "adode": "06",
                    "date": "29/01/2021",
                    "order": 31,
                    "repair_List": "safe"
                  },
                  {
                    "adode": "sa",
                    "date": "29/01/2021",
                    "order": 32,
                    "repair_List": "sa"
                  }
                ],
                "sumOrderByDate": 4
              },
              {
                "date": "26/01/2021",
                "orders": [
                  {
                    "adode": "60/303",
                    "date": "26/01/2021",
                    "order": 29,
                    "repair_List": "ds"
                  }
                ],
                "sumOrderByDate": 1
              },
              {
                "date": "30/01/2021",
                "orders": [
                  {
                    "adode": "sasasa",
                    "date": "30/01/2021",
                    "order": 30,
                    "repair_List": "sas"
                  }
                ],
                "sumOrderByDate": 1
              }
            ]
        """.trimIndent()
        val result2 = Gson().fromJson<HistoryModel2>(data, HistoryModel2::class.java)

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
                R.id.nav_home -> RepairFragment()
                R.id.nav_history -> HistoryFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HistoryFragment()
            }
            replaceFragment(selectedFragment)
            true
        }
}