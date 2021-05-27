package com.example.loginmvvm.presentation.history.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.base.Dru
import com.example.loginmvvm.base.Dru.loadImageCircle
import com.example.loginmvvm.data.request.ImagsRequest
import com.example.loginmvvm.presentation.history.HistoryFragment
import com.example.loginmvvm.presentation.profile.ProfileFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.frament_history.view.*
import kotlinx.android.synthetic.main.frament_profile.*
import java.util.*

class DetailActivity : BaseActivity() {

    private var mImageUri: Uri? = null
    private val mColumAdapter = ColumAdapter()
    private val mList = ListAdapter()
    private var jobid: Int? = null

    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val userId = baseContext?.getSharedPreferences(
            "file",
            AppCompatActivity.MODE_PRIVATE
        )?.getInt("id", 0)
        val idjob = intent.getIntExtra("orderid", 0)
        jobid = idjob


        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.list.observe(this, { list ->

            val adt = ConcatAdapter(
                mColumAdapter, mList
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
        viewModel.imgprofileModel.observe(this, { Imag ->
            if (Imag.img == null) {

                iv_photo_money.setImageResource(R.drawable.bank)
            } else if (Imag.img != null) {
                val baseUrl = Imag.img.toString()
                iv_photo_money.loadImageCircle(baseUrl)
                iv_photo_money.isEnabled=false
                Bt_save.isEnabled=false
            }

        })

        viewModel.statusModel.observe(this,{
            if(it.statusid==1){
                layoutpay.visibility=View.GONE
            }
        })
        viewModel.chekstatus(idjob)

        viewModel.chekImg(idjob)

        iv_photo_money.setOnClickListener {
            Log.d(TAG, "uri")
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 123)
        }

        Bt_save.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            Dru.uploadImage(baseContext, baseUrl, imageName, mImageUri) {
                // update url

                val upimags = jobid?.let { it1 -> ImagsRequest(it1, imagePath) }

                if (upimags != null) {
                    Log.d(TAG, "onActivityResult: $upimags")
//                    viewModel.chekImg(upimags)
                    viewModel.upImg(upimags)
                }

                Toast.makeText(baseContext, "${it?.response}", Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
            }
        }


    }

    private var baseUrl = "https://easyfix204.000webhostapp.com/image/"
    private var imageName = UUID.randomUUID().toString().replace("-", "") + ".jpg"
    private var imagePath = baseUrl + imageName
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            mImageUri = data.data
            iv_photo_money.loadImageCircle(mImageUri.toString())
            Log.d(TAG, "onCreate: ${imagePath}")
        }
    }

    companion object {
        private const val TAG = "Detail"
    }
}