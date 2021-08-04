package com.example.loginmvvm.presentation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.request.HomeRequest
import com.example.loginmvvm.data.models.ProfileModel
import com.example.loginmvvm.data.models.ProvincesModel
import com.example.loginmvvm.data.request.ImagsRequest

class ProfileViewModel : ViewModel() {

    private val _profileModel = MutableLiveData<ProfileModel>()
    val profileModel: LiveData<ProfileModel>
        get() = _profileModel


    private val _imgprofileModel = MutableLiveData<ProfileModel>()
    val imgprofileModel: LiveData<ProfileModel>
        get() = _imgprofileModel

    private val _provinces = MutableLiveData<List<ProvincesModel>>()
    val provinces: LiveData<List<ProvincesModel>>
        get() = _provinces


//    fun provinces(){
//        _provinces.value=DataSource.provinces()
//    }

    fun profile(userId: Int?) {
        userId?.let {
            _profileModel.value = DataSource.profile(it)
        }


    }

    fun uphome(req: HomeRequest){
        DataSource.abode(req)


    }

    fun chekImg(req: ImagsRequest) {
        DataSource.upimgprofile(req)
//        _profileModel.value=DataSource.profile(req.id)

        Log.d(TAG, "chekImg: ")

    }
    init {
        _provinces.value=DataSource.provinces()
    }



    companion object{
        private const val TAG = "####"
    }

}
