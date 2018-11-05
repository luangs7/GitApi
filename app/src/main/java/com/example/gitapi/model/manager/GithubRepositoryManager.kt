package com.example.gitapi.model.manager

import com.example.gitapi.view.activity.main.MainActivity
import com.example.gitapi.view.activity.main.MainActivityInteractor

class GithubRepositoryManager(val interactor:MainActivityInteractor) {

    fun getResultList() = interactor.result

    suspend fun getGithubUser(name:String) = interactor.getUserRequest(name)


}