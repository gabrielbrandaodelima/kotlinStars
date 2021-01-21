package com.gabriel.kotlinstars.features.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.platform.BaseFragment
import com.gabriel.kotlinstars.features.ui.viewmodel.GitReposViewModel

class GitReposFragment : BaseFragment(R.layout.fragment_git_repos) {

    private lateinit var viewModel: GitReposViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GitReposViewModel::class.java)
        // TODO: Use the ViewModel
    }

}