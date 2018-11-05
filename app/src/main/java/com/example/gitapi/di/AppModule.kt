package com.example.gitapi.di

import com.example.gitapi.model.manager.GithubRepositoryManager
import com.example.gitapi.model.viewmodel.GithubUserViewModel
import com.example.gitapi.retrofit.GithubAPI
import com.example.gitapi.view.activity.main.MainActivityInteractor
import com.example.gitapi.view.activity.main.MainActivityPresenter
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
        viewModel { GithubUserViewModel(get() as GithubRepositoryManager) }
        factory { GithubRepositoryManager(get() as MainActivityInteractor) }
        factory { MainActivityInteractor(get() as GithubAPI) }
        factory { MainActivityPresenter(get() as GithubUserViewModel) }
}