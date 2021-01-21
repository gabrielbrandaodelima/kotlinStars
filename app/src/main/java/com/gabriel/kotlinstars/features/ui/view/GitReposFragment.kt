package com.gabriel.kotlinstars.features.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.platform.BaseFragment
import com.gabriel.kotlinstars.databinding.FragmentGitReposBinding
import com.gabriel.kotlinstars.features.ui.adapter.GitReposAdapter
import com.gabriel.kotlinstars.features.ui.viewmodel.GitReposViewModel

class GitReposFragment : BaseFragment(R.layout.fragment_git_repos), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var viewModel: GitReposViewModel


    private var _binding: FragmentGitReposBinding? = null

    private val binding get() = _binding

    private var reposAdapter: GitReposAdapter? = null

    private var searchView: SearchView? = null

    private var reposGeneral: List<GitRepository>? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClose(): Boolean {
        TODO("Not yet implemented")
    }

}