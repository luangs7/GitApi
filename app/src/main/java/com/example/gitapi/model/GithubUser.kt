package com.example.gitapi.model


import com.google.gson.annotations.SerializedName

class GithubUser constructor(@SerializedName("login") val username: String = "", @SerializedName("id") val id: Int = 0,
                             @SerializedName("avatar_url") val imageUrl: String = "",
                             @SerializedName("repos_url") val repoUrl: String = "")
