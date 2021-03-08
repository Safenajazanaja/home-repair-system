package com.example.easyfix.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.easyfix.R
import com.example.easyfix.base.BaseActivity
import com.example.easyfix.data.request.LoginRequest
import com.example.easyfix.presentation.main.MainActivity
import com.example.easyfix.presentation.repair.MapActivity
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
                viewModel.id.observe(this,{a ->

                    val preferences = getSharedPreferences("file", Context.MODE_PRIVATE)
                    viewModel.id.let { preferences.edit().putInt("id",a).apply() }
                    Toast.makeText(baseContext, "ผ่าน", Toast.LENGTH_SHORT).show()
                    val intent=Intent(baseContext,MainActivity::class.java).putExtra("id",a)
                    startActivity(intent)

                })

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

        btSingup.setOnClickListener {
//            val intent=Intent(baseContext,Sing_UpActivity::class.java)
            val intent=Intent(baseContext,MapActivity::class.java)
            startActivity(intent)
        }

    }

}