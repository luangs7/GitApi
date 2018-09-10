package com.example.gitapi.view.activity.details

import com.example.gitapi.model.Repo

interface DetailsActivityContract {


    interface Presenter{
        fun injectView(view:DetailsActivityContract.View)
        fun getRepo(name:String)

    }



    interface View{
        fun bindComponents()
        fun loadList(list: List<Repo>)
        fun showLoading()
        fun hideLoading()
        fun refreshList()
        fun showError(error:String)

    }

}
