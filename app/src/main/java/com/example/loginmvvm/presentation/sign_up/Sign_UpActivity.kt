package com.example.loginmvvm.presentation.sign_up

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.base.onItemSelected
import com.example.loginmvvm.data.models.AmphurModel
import com.example.loginmvvm.data.models.DistrictModel
import com.example.loginmvvm.data.models.ProvincesModel
import com.example.loginmvvm.data.request.SingupRequest
import com.example.loginmvvm.presentation.login.LoginActivity
import com.example.loginmvvm.presentation.profile.SpinneramphurAdapter
import com.example.loginmvvm.presentation.profile.SpinnerdistrictAdapter
import com.example.loginmvvm.presentation.profile.SpinnerprovincesAdapter
import kotlinx.android.synthetic.main.activity_sing__up.*
import kotlinx.android.synthetic.main.fragment_call.*

class Sign_UpActivity : BaseActivity() {
    private lateinit var viewModel: SignViewModel


    private lateinit var type2: ProvincesModel
    private var name2: String? = null
    private var id2: Int? = null

    private lateinit var type3: AmphurModel
    private var name3: String? = null
    private var id3: Int? = null

    private lateinit var type4: DistrictModel
    private var name4: String? = null
    private var id4: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing__up)

        viewModel = ViewModelProvider(this).get(SignViewModel::class.java)
        viewModel.toast.observe(this, { str ->
            Toast.makeText(baseContext, "$str", Toast.LENGTH_SHORT).show()
        })

        viewModel.singup.observe(this, { b ->
            if (b) {
                Toast.makeText(baseContext, "ผ่าน", Toast.LENGTH_SHORT).show()
                val intent= Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(baseContext, "Usernameซ้ำกรุณากรอกข้อมูลใหม่", Toast.LENGTH_SHORT)
                    .show()
            }
        })




        Sing_up.setOnClickListener {
            val Username = etUsernameSing.text.toString().trim()
            val Password = etPassword.text.toString().trim()
            val FullName= etFullnameSing.text.toString().trim()
            val Repassword=etRePassword.text.toString().trim()
            val Phone=etPhoneSing.text.toString().trim()
            val  amphurId =type3.id
            val  provincesId = type2.id
            val districtId = type4.id
            val home= etHome.text.toString().trim()
            val Singup =SingupRequest(
                username=Username,
                password=Password,
                repassword=Repassword,
                fullname=FullName,
                phone=Phone,
                amphurId=amphurId,
                provincesId=provincesId,
                districtId=districtId,
                home = home
            )
            viewModel.singup(Singup)
        }



        bar_provinces_up.adapter = SpinnerprovincesAdapter(
            baseContext,
            viewModel.provinces.value as MutableList<ProvincesModel>
        )
        bar_provinces_up.onItemSelected<ProvincesModel> {
            type2 = it
            id2 = it.id
            name2 = it.name
            viewModel.amphurselect(it.id)
            bar_amphur_up.adapter = SpinneramphurAdapter(
                baseContext,
                viewModel.amphur.value as MutableList<AmphurModel>
            )
            bar_amphur_up.onItemSelected<AmphurModel> {
                type3 = it
                id3 = it.id
                name3 = it.name

                viewModel.districtselet(it.id)
                bar_districts_up.adapter = SpinnerdistrictAdapter(
                    baseContext,
                    viewModel.district.value as MutableList<DistrictModel>
                )
                bar_districts_up.onItemSelected<DistrictModel> {
                    type4 = it
                    id4 = it.id
                    name4 = it.name
                }
            }
        }

        bar_amphur_up.adapter = SpinneramphurAdapter(
            baseContext,
            viewModel.amphur.value as MutableList<AmphurModel>
        )
        bar_amphur_up.onItemSelected<AmphurModel> {
            type3 = it
            id3 = it.id
            name3 = it.name
        }

        bar_districts_up.adapter = SpinnerdistrictAdapter(
            baseContext,
            viewModel.district.value as MutableList<DistrictModel>

        )
        bar_districts_up.onItemSelected<DistrictModel> {
            type4 = it
            id4 = it.id
            name4 = it.name
        }
        val amphurId = 1
        val provincesId = 1
        val districtId =1


        bar_provinces_up.setSelection(provincesId)
        bar_amphur_up.setSelection(amphurId)
        bar_districts_up.setSelection(districtId)
    }
}