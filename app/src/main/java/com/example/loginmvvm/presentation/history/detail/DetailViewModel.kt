package com.example.loginmvvm.presentation.history.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginmvvm.data.datasource.DataSource
import com.example.loginmvvm.data.models.*
import com.example.loginmvvm.data.request.ImagsRequest
import com.google.gson.Gson

class DetailViewModel : ViewModel() {

    private var _list = MutableLiveData<List<ListModel>>()
    val list: LiveData<List<ListModel>>
        get() = _list
    private val _imgpayModel = MutableLiveData<ImagModel>()
    val imgpayModel: LiveData<ImagModel>
        get() = _imgpayModel

    private val _payModel = MutableLiveData<PayModel>()
    val payModel: LiveData<PayModel>
        get() = _payModel

    private val _statusModel = MutableLiveData<StatusModel>()
    val statusModel: LiveData<StatusModel>
        get() = _statusModel

    private val _sumprice=MutableLiveData<List<SumpriceModel>>()
    val sumprice:LiveData<List<SumpriceModel>>
    get() = _sumprice
    private var _pricetec = MutableLiveData<Int>()
    val pricetec: LiveData<Int>
        get() = _pricetec


    private var _workjob = MutableLiveData<ManageModel>()
    val workjob: LiveData<ManageModel>
        get() = _workjob

    fun listdetail(request: Int) {
        val result = DataSource.listitem(request)

        val sss= result.map {db->
            SumpriceModel(
                sum = db.qty!! * db.Unitprice!!
            )

        }

        Log.d(TAG, "ssss: ${Gson().toJson(sss)}")
        _sumprice.value=sss
        _list.value = result
    }

    fun chekpricetec(idjob:Int){
        val result=DataSource.chekpricetec(idjob)
        _pricetec.value=result.price


    }

    fun upImg(req: ImagsRequest) {
        DataSource.upimgpay(req)
        Log.d(TAG, "chekImg: ")

    }

    fun chekImg(jobid: Int) {
        _imgpayModel.value = DataSource.chekImage(jobid)
        _payModel.value=DataSource.chekPay(jobid)

    }

    fun chekstatus(jobid: Int) {
        _statusModel.value = DataSource.chekStatus(jobid)
    }
    fun mana(idjob: Int) {
        _workjob.value = DataSource.manage(idjob)
    }

    companion object {
        private const val TAG = "www"
    }
}