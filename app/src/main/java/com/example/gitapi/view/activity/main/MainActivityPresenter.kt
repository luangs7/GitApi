package com.example.gitapi.view.activity.main

import com.example.gitapi.model.GithubUser
import com.example.gitapi.retrofit.CallbackWrapper
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
        view.hideKeyboard()
        view.showProgress()

        subscription.add(api.getUser(name)
                .compose(rxThread.applyAsync())
                .doOnTerminate {
                    view.hideProgress()
                }
                .subscribe({
                    view.showSuccess(it)
                }, {
                    CallbackWrapper(it).onFailure()?.let { view.showError(it) }
                })
        )


    }


    override fun getUserInfoError(error: String) {
        view.showError(error)
    }
}