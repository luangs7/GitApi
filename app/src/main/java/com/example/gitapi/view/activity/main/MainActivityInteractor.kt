package com.example.gitapi.view.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.luan2.lgutilsk.utils.error
import com.example.gitapi.model.GithubUser
import com.example.gitapi.retrofit.CallbackWrapper
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.retrofit.Resource
import io.reactivex.disposables.CompositeDisposable

class MainActivityInteractor(val api: GithubAPI){

    private val _result = MutableLiveData<Resource<GithubUser>>()

    val result
        get() = _result as LiveData<Resource<GithubUser>>


    suspend fun getUserRequest(name:String) {
        _result.value = Resource.loading(null)


        val response = api.getUser(name).await()

        if(response.isSuccessful){
            _result.postValue(Resource.success(response.body()))
        }
        else{
            _result.postValue(Resource.error(response.errorBody()!!.string(),null))
        }
    }






}