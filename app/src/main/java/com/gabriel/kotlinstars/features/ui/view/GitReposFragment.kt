package com.gabriel.kotlinstars.features.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.di.DaggerComponent
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.extensions.failure
import com.gabriel.kotlinstars.core.extensions.observe
import com.gabriel.kotlinstars.core.extensions.setUpRecyclerView
import com.gabriel.kotlinstars.core.extensions.viewModel
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentGitReposBinding.inflate(inflater, container, false)
        return _binding?.root ?: View(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerComponent.create().inject(this)
        setUpRecycler()
        initViewModel()

    }

    private fun setUpRecycler() {

        binding?.reposRecycler?.apply {
            setUpRecyclerView(requireContext(), {
                reposAdapter = GitReposAdapter(arrayListOf(), ::navigateToDetail)
                adapter = reposAdapter
            })

        }

    }

    private fun navigateToDetail(gitRepository: GitRepository) {
        val bundle = bundleOf("repo" to gitRepository)
        findNavController().navigate(R.id.gitRepoDetailFragment, bundle)
    }

    private fun initViewModel() {
        viewModel = viewModel(viewModelFactory) {
            observe(gitRepos, ::handleSuccessList)
            failure(failure, ::handleFailure)
        }
        viewModel.fetchRepos()
    }

    private fun handleSuccessList(list: List<GitRepository>?) {
        TODO("Not yet implemented")
    }

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