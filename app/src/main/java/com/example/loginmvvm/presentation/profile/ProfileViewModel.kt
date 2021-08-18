package com.example.loginmvvm.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.AmphurModel
import com.example.loginmvvm.data.models.DistrictModel
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


    private val _amphur = MutableLiveData<List<AmphurModel>>()
    val amphur: LiveData<List<AmphurModel>>
        get() = _amphur

    private val _district = MutableLiveData<List<DistrictModel>>()
    val district: LiveData<List<DistrictModel>>
        get() = _district

//    fun provinces(){
//        _provinces.value=DataSource.provinces()
//    }
    fun profile(userId: Int?) {
        userId?.let {
            _profileModel.value = DataSource.profile(it)
        }


    }
    fun amphurselect(amphurid:Int){
        _amphur.value=DataSource.amphurselect(amphurid)

    }
    fun districtselet(districtid:Int){
        _district.value=DataSource.districtselect(districtid)
    }

    fun uphome(req: HomeRequest){
        DataSource.abode(req)


    }
    fun chekImg(req: ImagsRequest) {
        DataSource.upimgprofile(req)
//        _profileModel.value=DataSource.profile(req.id)
    }
    init {
        _provinces.value=DataSource.provinces()
        _amphur.value=DataSource.amphur()
        _district.value=DataSource.district()
    }
    companion object{
        private const val TAG = "####"
    }

}
