package com.example.loginmvvm.presentation.sign_up

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.data.request.SingupRequest
import com.example.loginmvvm.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_sing__up.*

class Sign_UpActivity : BaseActivity() {
    private lateinit var viewModel: SignViewModel
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
            val Singup =SingupRequest(Username, Password,Repassword,FullName,Phone)
            viewModel.singup(Singup)
        }
    }
}