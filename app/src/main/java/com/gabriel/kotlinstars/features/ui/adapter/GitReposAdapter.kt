package com.gabriel.kotlinstars.features.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.extensions.loadFromUrl
import com.gabriel.kotlinstars.databinding.ItemGitReposBinding

class GitReposAdapter(
    var gitRepos: ArrayList<GitRepository>,
    var click: (gitRepo: GitRepository) -> Unit
) : RecyclerView.Adapter<GitReposAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemGitReposBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gitRepo = gitRepos[position]
        holder.bindView(gitRepo)
    }

    override fun getItemCount(): Int {
        return gitRepos.size
    }

    internal fun addAll(list: ArrayList<GitRepository>) {
        gitRepos = arrayListOf()
        gitRepos = list
        notifyDataSetChanged()
    }

    internal fun appendAll(linksList: ArrayList<GitRepository>) {
        val startindex = gitRepos.size
        gitRepos.addAll(startindex, linksList)
        notifyItemRangeInserted(startindex, linksList.size)
    }

    fun clear() {
        gitRepos.clear()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: ItemGitReposBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val view: ItemGitReposBinding = itemView

        fun bindView(
            gitRepository: GitRepository
        ) {
            with(view) {
                gitRepository.owner?.avatar_url?.let { repoImg.loadFromUrl(it) }
                repoName.text = gitRepository.full_name
                repoUsername.text = gitRepository.owner?.login
                repoCell.setOnClickListener { click.invoke(gitRepository) }
            }
        }
    }
}