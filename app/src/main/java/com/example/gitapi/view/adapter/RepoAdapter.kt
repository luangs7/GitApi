package com.example.gitapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.model.Repo
import com.example.gitapi.R

class RepoAdapter constructor(private val repositories: List<Repo>,
                              private val clickRepository: OnClickRepository)
    : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_row, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository, clickRepository)
    }

    override fun getItemCount(): Int = repositories.size

}
