package com.example.gitapi.view.activity.main

import com.example.gitapi.model.GithubUser
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription
import javax.inject.Inject

class MainActivityPresenter
    @Inject constructor(private val api: GithubAPI, private val rxThread: RxThread) : MainActivityContract.Presenter {

    lateinit var view:MainActivityContract.View

     var subscription = CompositeDisposable()

    fun injectView(view: MainActivityContract.View){
        this.view = view
        view.bindViews()
    }


    override fun getUserInfo(name: String) {
        view.showProgress()

        subscription.add(api.getUser(name)
                .compose(rxThread.applyAsync())
                .doOnTerminate {
                    view.hideProgress()
                    view.hideKeyboard()
                }
                .subscribe({
                    view.showSuccess(it)
                }, {
                    view.showError(it.message!!)
                })
        )


    }


    override fun getUserInfoError(error: String) {
        view.showError(error)
    }
}