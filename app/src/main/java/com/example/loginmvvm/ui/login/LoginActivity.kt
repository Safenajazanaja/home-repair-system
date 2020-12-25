package com.example.loginmvvm.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.data.models.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.toast.observe(this, { str ->
            Toast.makeText(baseContext, "$str", Toast.LENGTH_SHORT).show()
        })

        viewModel.login.observe(this, { b ->
            if (b) {
                Toast.makeText(baseContext, "ผ่าน", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(baseContext, "ตรวจสอบอีกครั้ง", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        btlogin.setOnClickListener {
            val Username = etUsername.text.toString().trim()
            val Password = etPassword.text.toString().trim()
            val Login = LoginRequest(Username, Password)
            viewModel.login(Login)
        }

//        btSingup.setOnClickListener {
//            startActivity(R.layout.)
//        }

    }

}