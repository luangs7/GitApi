package com.example.gitapi.retrofit

import com.example.gitapi.model.GithubUser
import com.example.gitapi.model.Repo
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Deferred<Response<GithubUser>>

    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Deferred<Response<List<Repo>>>
}
