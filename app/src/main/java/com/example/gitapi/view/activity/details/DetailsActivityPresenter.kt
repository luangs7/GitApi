package com.example.gitapi.view.activity.details

import com.example.gitapi.model.Repo
import com.example.gitapi.retrofit.CallbackWrapper
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsActivityPresenter
@Inject constructor(private val api: GithubAPI, private val rxThread: RxThread)  : DetailsActivityContract.Presenter{

    lateinit var view: DetailsActivityContract.View
    var subscription = CompositeDisposable()


    override fun injectView(view: DetailsActivityContract.View) {
        this.view = view
        view.bindComponents()
    }

    override fun getRepo(name: String) {

        view.showLoading()

        subscription.add(api.getRepo(name)
                .compose(rxThread.applyAsync())
                .doOnTerminate {
                    view.hideLoading()
                }
                .subscribe({
                    view.loadList(it)
                }, {
                    CallbackWrapper(it).onFailure()?.let { view.showError(it) }
                })
        )

    }
}