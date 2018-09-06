package com.example.gitapi.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Component


@Component(modules = [ActivityModule::class, RxThreadModule::class])
interface ActivityComponent {

    fun inject(activity: AppCompatActivity)

}