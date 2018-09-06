package com.example.daggerex

import com.example.gitapi.di.ActivityModule
import com.example.gitapi.view.activity.main.MainActivity
import com.example.gitapi.di.RxThreadModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class,ActivityModule::class, RxThreadModule::class))
interface NetComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}