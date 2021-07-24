package com.example.loginmvvm.presentation.trace

import android.content.Intent
import android.nfc.Tag
import android.nfc.tech.TagTechnology
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleExpandableListAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseFragment
import com.example.loginmvvm.presentation.history.detail.DetailActivity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_trace.*
import kotlinx.android.synthetic.main.frament_history.*
import java.time.Instant

class TraceFragment : BaseFragment(R.layout.fragment_trace) {

    private lateinit var viewModel: TraceViewModel
    private lateinit var mTraceAdapter: TraceAdepter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TraceViewModel::class.java)
        viewModel.toast.observe(this,{str ->
            Toast.makeText(requireContext(),"$str",Toast.LENGTH_SHORT).show()

        })
        val userId =
            context?.getSharedPreferences("file",
                AppCompatActivity.MODE_PRIVATE
            )?.getInt("id",0)


        userId?.toInt()?.let { viewModel.tracejob(it) }

        viewModel.trace.observe(this,{ trace ->

            mTraceAdapter= TraceAdepter()
            expandableListViewtrace.setAdapter(mTraceAdapter)
            Log.d(TAG, "trace: ${Gson().toJson(trace)}")
            mTraceAdapter.setList(trace)
            mTraceAdapter.setOnClickListener {
                val intent = Intent(context,DetailActivity::class.java).apply {
                    putExtra("orderid",it.orderid)
                }
                startActivity(intent)
            }


        })
    }

    companion object{
        private const val TAG ="TraceFragment"
    }
}