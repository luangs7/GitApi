package com.example.gitapi


import android.app.Application
import com.example.daggerex.ApplicationModule
import com.example.daggerex.DaggerNetComponent
import com.example.daggerex.NetComponent
import com.example.daggerex.NetModule
import com.example.gitapi.di.ActivityComponent
import com.example.gitapi.di.ActivityModule
import com.example.gitapi.di.DaggerActivityComponent

class App : Application() {

    lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
                .applicationModule(ApplicationModule(this))
                .netModule(NetModule("https://jsonplaceholder.typicode.com/"))
                .build()
    }

}