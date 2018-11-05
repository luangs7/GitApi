package com.example.gitapi


import android.app.Application
import com.example.gitapi.di.appModule
import com.example.gitapi.di.networkModule
import org.koin.android.ext.android.startKoin


class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, networkModule))
    }

}