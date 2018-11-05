package com.example.gitapi.view.activity.main

import androidx.lifecycle.Observer
import com.example.gitapi.model.GithubUser
import com.example.gitapi.model.viewmodel.GithubUserViewModel


class MainActivityPresenter(val viewModel: GithubUserViewModel) : MainActivityContract.Presenter {
    lateinit var view: MainActivityContract.View

    fun injectView(view: MainActivityContract.View) {
        this.view = view
        view.bindViews(viewModel)
    }


    override  fun getUserInfo(name: String) {
        viewModel.getGithubUsers(name)
//        viewModel.repositoryResult.observe(this, Observer { it -> it.data?.let { view.showSuccess(it) } })
    }





    override fun getUserInfoError(error: String) {
        view.showError(error)
    }
}