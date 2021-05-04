package com.example.loginmvvm.presentation.history.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginmvvm.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.frament_history.view.*

class DetailActivity : AppCompatActivity() {

    private  val mColumAdapter=ColumAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val adt = ConcatAdapter(mColumAdapter)
        recylerView.apply {
            layoutManager=LinearLayoutManager(baseContext)
            adapter=adt
        }
        mColumAdapter.submitData(Unit)
    }
}