package com.example.loginmvvm.presentation.sign_up

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.request.SingupRequest
import com.example.loginmvvm.data.response.SingupResponse

class SignViewModel : ViewModel() {

    private lateinit var viewModelRes: SingupResponse

    private var _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private var _singup = MutableLiveData<Boolean>()
    val singup: LiveData<Boolean>
        get() = _singup

    fun singup(request: SingupRequest) {
        when {
            request.username.isBlank() -> _toast.value = "Username blank"
            request.password.isBlank() -> _toast.value = "Password blank"
            request.fullname.isBlank()-> _toast.value = "Fullname blank"
            request.phone.isBlank()-> _toast.value = "Phone blank"
            request.password.isBlank()-> _toast.value = "Password blank"
            request.repassword.isBlank()-> _toast.value = "Re PassWord blank"
//            Patterns.EMAIL_ADDRESS.matcher(request.username).matches() -> _toast.value="กรุณาตรวจสอบอีกครั้ง"


//            request.password.length <6 ->  _toast.value="Passwordต้องมากกว่า7ตัว"
//            request.repassword.length <6 -> _toast.value = "RePasswordต้องมากกว่า7ตัว"
//            request.username.length <6 -> _toast.value="Usernameต้องมากกว่า7ตัว"

            else -> {
                val result = DataSource.sing_up(request)
                _singup.value = result.success
            }

        }

    }
}