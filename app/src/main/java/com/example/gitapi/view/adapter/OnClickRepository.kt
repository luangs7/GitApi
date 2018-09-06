package com.example.gitapi.adapter

import com.example.gitapi.model.Repo

interface OnClickRepository {
    fun onClickRepoItem(repo: Repo)
}