package com.example.easyfix.presentation.sing_up

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.easyfix.R
import com.example.easyfix.base.BaseActivity
import com.example.easyfix.data.request.SingupRequest
import com.example.easyfix.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_sing__up.*

class Sing_UpActivity : BaseActivity() {
    private lateinit var viewModel: SingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing__up)

        viewModel = ViewModelProvider(this).get(SingViewModel::class.java)
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
            val Singup =SingupRequest(Username, Password,Repassword,FullName,Phone)
            viewModel.singup(Singup)
        }
    }
}