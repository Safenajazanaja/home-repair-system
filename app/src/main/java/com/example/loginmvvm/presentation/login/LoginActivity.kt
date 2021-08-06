package com.example.loginmvvm.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.R
import com.example.loginmvvm.base.BaseActivity
import com.example.loginmvvm.data.request.LoginRequest
import com.example.loginmvvm.presentation.homepage.HomepageFragment
import com.example.loginmvvm.presentation.main.MainActivity
import com.example.loginmvvm.presentation.sign_up.Sign_UpActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val preferences = getSharedPreferences("file", Context.MODE_PRIVATE)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.toast.observe(this, { str ->
//            Toast.makeText(baseContext, "$str",Toast.LENGTH_SHORT).show()

            Toasty.Config.getInstance().setTextSize(30)
            Toasty.warning(baseContext,"กรุณาตรวจสอบอีกครั้ง",Toast.LENGTH_SHORT).show()
            preferences.edit().clear()

        })

        viewModel.login.observe(this, { b ->
            if (b) {
                viewModel.id.observe(this, { a ->

//                    val preferences = getSharedPreferences("file", Context.MODE_PRIVATE)



                    viewModel.id.let { preferences.edit().putInt("id", a).apply() }
                    Toasty.Config.getInstance().setTextSize(30)
                    Toasty.success(baseContext,"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT).show()
                    val intent = Intent(baseContext, HomepageFragment::class.java).putExtra("id", a)
                    startActivity(intent)

                })

            } else {
                preferences.edit().clear()
                Toasty.Config.getInstance().setTextSize(30)
                Toasty.warning(baseContext,"กรุณาตรวจสอบอีกครั้ง",Toast.LENGTH_SHORT).show()
            }
        })

        btlogin.setOnClickListener {
            val Username = etUsername.text.toString().trim()
            val Password = etPassword.text.toString().trim()
            val Login = LoginRequest(Username, Password)
            viewModel.login(Login)

        }

        btSignup.setOnClickListener {
            val intent=Intent(baseContext, Sign_UpActivity::class.java)
//            val intent=Intent(baseContext,MapActivity::class.java)
            startActivity(intent)
        }

    }

}