package com.example.loginmvvm.presentation.history


import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginmvvm.R
import com.example.loginmvvm.data.request.HistoryDetailRequest
import kotlinx.android.synthetic.main.activity_detail_history.*
import android.content.ContentValues.TAG

class DetailHistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)

        val userId = intent.getIntExtra("file", 0)
        val datesum=intent.getLongExtra("date",0)



        viewModel=ViewModelProvider(this).get(DetailHistoryViewModel::class.java)
        viewModel.historysum.observe(this,{str ->
            val adt=DetailHistoryAdapter()
            recyclerViewDetail.apply {
                layoutManager=LinearLayoutManager(context)
                adapter=adt
            }
            adt.setList(str)

        })

        viewModel.historydetail(req= HistoryDetailRequest(id = userId,date = datesum))

    }
}