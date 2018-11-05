package com.example.gitapi.view.activity.main

import com.example.gitapi.model.GithubUser
import com.example.gitapi.model.viewmodel.GithubUserViewModel

interface MainActivityContract {

    interface Presenter{
        fun getUserInfo(name: String)
        fun getUserInfoError(error:String)

    }

    interface Interactor {

        interface UserRequestInfo{
            fun UserRequestInfoSucces(githubUser: GithubUser)
            fun UserRequestInfoError(error: String)
        }

        fun getUserRequest(name:String, listener:UserRequestInfo)

    }

    interface View{
        fun showError(error: String)
        fun showSuccess(githubUser: GithubUser)
        fun hideKeyboard()
        fun showProgress(message:String? = "Buscando dados")
        fun hideProgress()
        fun bindViews(viewModel:GithubUserViewModel)

    }


}