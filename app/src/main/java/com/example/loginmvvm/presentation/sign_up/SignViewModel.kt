package com.example.loginmvvm.presentation.sign_up

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.AmphurModel
import com.example.loginmvvm.data.models.DistrictModel
import com.example.loginmvvm.data.models.ProvincesModel
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

    private val _provinces = MutableLiveData<List<ProvincesModel>>()
    val provinces: LiveData<List<ProvincesModel>>
        get() = _provinces


    private val _amphur = MutableLiveData<List<AmphurModel>>()
    val amphur: LiveData<List<AmphurModel>>
        get() = _amphur

    private val _district = MutableLiveData<List<DistrictModel>>()
    val district: LiveData<List<DistrictModel>>
        get() = _district

    fun singup(request: SingupRequest) {
        when {
            request.username.isBlank() -> _toast.value = "Username blank"
            request.password.isBlank() -> _toast.value = "Password blank"
            request.fullname.isBlank()-> _toast.value = "Fullname blank"
            request.phone.isBlank()-> _toast.value = "Phone blank"
            request.password.isBlank()-> _toast.value = "Password blank"
            request.repassword.isBlank()-> _toast.value = "Re PassWord blank"
            request.home.isBlank() -> _toast.value="Home blank"
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

    fun amphurselect(amphurid:Int){
        _amphur.value=DataSource.amphurselect(amphurid)

    }

    fun districtselet(districtid:Int){
        _district.value=DataSource.districtselect(districtid)
    }

    init {
//        seletjob()
        _provinces.value = DataSource.provinces()
        _amphur.value = DataSource.amphur()
        _district.value = DataSource.district()


    }
}