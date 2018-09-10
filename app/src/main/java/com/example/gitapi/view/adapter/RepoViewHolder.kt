package com.example.gitapi.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


import com.example.gitapi.R
import com.example.gitapi.model.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvName by lazy { view.findViewById(R.id.tvName) as TextView }
    val card by lazy { view.findViewById(R.id.card) as RelativeLayout }
    val tvLanguage by lazy { view.findViewById(R.id.tvLanguage) as TextView }

    fun bind(repo: Repo, clickRepository: OnClickRepository) {
        tvName.text = repo.nameRepo
        tvLanguage.text = repo.language

        card.setOnClickListener { clickRepository.onClickRepoItem(repo) }
    }
}
