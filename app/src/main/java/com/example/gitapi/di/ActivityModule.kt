package com.example.gitapi.di

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Singleton
    @Provides
    fun provideContext():Context = activity

    @Singleton
    @Provides
    fun provideActivity():Activity = activity
}