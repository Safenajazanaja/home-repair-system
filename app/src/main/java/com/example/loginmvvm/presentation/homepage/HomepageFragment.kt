package com.example.loginmvvm.presentation.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.presentation.main.HistoryMain
import com.example.loginmvvm.presentation.main.ProfileMain
import com.example.loginmvvm.presentation.main.RepairMain
import com.example.loginmvvm.presentation.main.TraceMain
import com.example.loginmvvm.presentation.profile.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_homepage.*

class HomepageFragment : BaseActivity() {
    private var user: Int? = null
    private lateinit var viewModel: HomepageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_homepage)

        val userId = baseContext.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)

        user = userId
        viewModel = ViewModelProvider(this).get(HomepageViewModel::class.java)

        viewModel.profile(userId)

        viewModel.nameModel.observe(this,{ name ->
            tv_namehome.text=name.name

        })

        bt_call.setOnClickListener {
            val intent = Intent(baseContext, RepairMain::class.java).putExtra("id", userId)
            startActivity(intent)
        }

        bt_trace.setOnClickListener {
            val intent = Intent(baseContext, TraceMain::class.java).putExtra("id", userId)
            startActivity(intent)
        }

        bt_his.setOnClickListener {
            val intent = Intent(baseContext, HistoryMain::class.java).putExtra("id", userId)
            startActivity(intent)
        }

        bt_profile.setOnClickListener {
            val intent = Intent(baseContext, ProfileMain::class.java).putExtra("id", userId)
            startActivity(intent)
        }


    }

}