package com.example.easyfix.presentation.history


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyfix.R
import com.example.easyfix.data.request.HistoryDetailRequest
import kotlinx.android.synthetic.main.activity_detail_history.*
import java.text.SimpleDateFormat

class DetailHistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)

//        val userId = intent.getIntExtra("file", 0)
        val userId = getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)?:0
        val datesum=intent.getLongExtra("date",0)

        val sdf = SimpleDateFormat("dd/MM/yyyy")

        tv_date_detail.text=sdf.format(datesum).toString()



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