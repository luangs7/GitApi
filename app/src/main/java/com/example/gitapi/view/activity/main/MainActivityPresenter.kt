package com.example.gitapi.view.activity.main

import com.example.gitapi.model.GithubUser
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityPresenter
    @Inject constructor(private val api: GithubAPI, private val rxThread: RxThread,private val interactor: MainActivityInteractor) : MainActivityContract.Presenter {

    lateinit var view:MainActivityContract.View

     var subscription = CompositeDisposable()

    fun injectView(view: MainActivityContract.View){
        this.view = view
        view.bindViews()
    }


    override fun getUserInfo(name: String) {
        view.hideKeyboard()
        view.showProgress()

        interactor.getUserRequest(name,object : MainActivityContract.Interactor.UserRequestInfo{
            override fun UserRequestInfoSucces(githubUser: GithubUser) {
                view.showSuccess(githubUser)
                view.hideProgress()
            }

            override fun UserRequestInfoError(error: String) {
                view.showError(error)
                view.hideProgress()
            }
        })
    }


    override fun getUserInfoError(error: String) {
        view.showError(error)
    }
}