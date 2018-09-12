package com.example.gitapi.view.activity.main

import com.example.gitapi.retrofit.CallbackWrapper
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class MainActivityInteractor
   @Inject constructor(var rxThread:RxThread, private val api: GithubAPI ) :MainActivityContract.Interactor{

    val subscription = CompositeDisposable()


    override fun getUserRequest(name:String,listener: MainActivityContract.Interactor.UserRequestInfo) {
        subscription.add(api.getUser(name)
                .compose(rxThread.applyAsync())
                .subscribe({
                    listener.UserRequestInfoSucces(it)
                }, {
                    CallbackWrapper(it).onFailure()?.let { listener.UserRequestInfoError(it) }
                })
        )
    }
}