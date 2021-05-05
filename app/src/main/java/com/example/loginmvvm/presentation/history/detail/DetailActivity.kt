package com.example.loginmvvm.presentation.history.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.presentation.history.HistoryFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.frament_history.view.*

class DetailActivity : BaseActivity() {

    private val mColumAdapter = ColumAdapter()
    private val mList = ListAdapter()

    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val userId =baseContext?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)


        val idjob= intent.getIntExtra("orderid",0)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.list.observe(this, { list ->

            val adt = ConcatAdapter(
                mColumAdapter,mList
            )
            recylerView.apply {
                layoutManager = LinearLayoutManager(baseContext)
                adapter = adt
            }
            mColumAdapter.submitData(Unit)
            mList.submitList(list)

            Log.d(TAG, "repair2: ${Gson().toJson(list)}")
            Log.d(TAG, "repair3: ${Gson().toJson(mList.submitList(list))}")
        })

        viewModel.listdetail(idjob)


    }
    companion object {
        private const val TAG = "Detail"
    }
}