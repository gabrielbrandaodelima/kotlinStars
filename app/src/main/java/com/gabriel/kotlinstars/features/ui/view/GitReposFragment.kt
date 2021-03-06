package com.gabriel.kotlinstars.features.ui.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.di.DaggerComponent
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.extensions.*
import com.gabriel.kotlinstars.core.platform.BaseFragment
import com.gabriel.kotlinstars.databinding.FragmentGitReposBinding
import com.gabriel.kotlinstars.features.ui.adapter.GitReposAdapter
import com.gabriel.kotlinstars.features.ui.viewmodel.GitReposViewModel

class GitReposFragment : BaseFragment(R.layout.fragment_git_repos), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var viewModel: GitReposViewModel


    private val binding by viewBinding(FragmentGitReposBinding::bind)

    private var reposAdapter: GitReposAdapter? = null

    private var searchView: SearchView? = null

    private var reposGeneral: List<GitRepository>? = null

    private var needsLoading = MutableLiveData(true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        DaggerComponent.create().inject(this)
        setUpRecycler()
        initViewModel()

    }

    private fun setUpRecycler() {

        binding.reposRecycler.apply {
            setUpRecyclerView(requireContext(), {
                reposAdapter = GitReposAdapter(arrayListOf(), ::navigateToDetail)
                adapter = reposAdapter
            })

            setUpPaging(needsLoading) {
                viewModel.fetchRepos()
            }

        }

    }

    private fun navigateToDetail(gitRepository: GitRepository) {
        val bundle = bundleOf("repo" to gitRepository)
        findNavController().navigate(R.id.gitRepoDetailFragment, bundle)
    }

    private fun initViewModel() {
        viewModel = viewModel(viewModelFactory) {
            observe(gitRepos, { it?.let { it1 -> handleSuccessList(it1) } })
            observe(pageLoading, {
                it?.let { it1 -> managePageProgress(it1) }
            })
            observe(loading, {
                it?.let { it1 -> manageProgress(it1) }
            })
            failure(failure, ::handleFailure)
        }
        viewModel.fetchRepos()
    }

    private fun managePageProgress(loading: Boolean) {
        binding.reposProgress.visibility(loading)
    }

    private fun manageProgress(loading: Boolean) {
        if (loading) {
            binding.reposRecycler.gone()
            binding.progress.visible()
        } else {
            binding.reposRecycler.visible()
            binding.progress.gone()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        searchView = menu.findItem(R.id.search).actionView as? SearchView
        searchView?.setOnQueryTextListener(this)
    }

    private fun handleSuccessList(pair: Pair<List<GitRepository>?, Int>) {
        needsLoading.postValue(true)
        val repos = pair.first
        val page = pair.second
        if (page == 1) {
            reposGeneral = repos
            reposAdapter?.addAll(repos as ArrayList<GitRepository>)
        } else {

            repos?.toMutableList()?.let { reposGeneral?.toMutableList()?.addAll(it) }
            reposAdapter?.appendAll(repos as ArrayList<GitRepository>)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filter(newText)
        return false
    }

    override fun onClose(): Boolean {
        resetAdapter()
        return false
    }

    private fun filter(query: String?) {
        if (query != null && query.isNotEmpty()) {
            val filtered = reposAdapter?.gitRepos?.filter { it.full_name?.contains(query) == true }
            reposAdapter?.addAll(filtered as ArrayList<GitRepository>)
        } else
            resetAdapter()
    }

    private fun resetAdapter() {
        reposAdapter?.addAll(reposGeneral as ArrayList<GitRepository>)
    }

}