package com.example.gitapi.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapi.model.GithubUser
import com.example.gitapi.model.manager.GithubRepositoryManager
import com.example.gitapi.retrofit.CallbackWrapper
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.retrofit.Resource
import com.example.gitapi.view.activity.main.MainActivityContract
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class GithubUserViewModel(val manager: GithubRepositoryManager) : BaseViewModel(){

    val innerRepositoryResult = MutableLiveData<Resource<List<GithubUser>>>()
    val innerResult
        get() = innerRepositoryResult as LiveData<Resource<List<GithubUser>>>

    val repositoryResult = manager.getResultList()

    fun getGithubUsers(name:String){
        this.launch(coroutineContext) {
            manager.getGithubUser(name)
        }
    }

}