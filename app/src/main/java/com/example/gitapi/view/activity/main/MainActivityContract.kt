package com.example.gitapi.view.activity.main

import com.example.gitapi.model.GithubUser

interface MainActivityContract {

    interface Presenter{
        fun getUserInfo(name: String)
        fun getUserInfoError(error:String)

    }

    interface View{
        fun showError(error: String)
        fun showSuccess(githubUser: GithubUser)
        fun hideKeyboard()
        fun showProgress(message:String? = "Buscando dados")
        fun hideProgress()
        fun bindViews()

    }


}