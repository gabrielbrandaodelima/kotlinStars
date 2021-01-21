package com.gabriel.kotlinstars.features.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.extensions.loadFromUrl
import com.gabriel.kotlinstars.core.extensions.viewBinding
import com.gabriel.kotlinstars.core.platform.BaseFragment
import com.gabriel.kotlinstars.databinding.FragmentGitRepoDetailBinding
import com.gabriel.kotlinstars.features.ui.viewmodel.GitReposViewModel

class GitRepoDetailFragment : BaseFragment(R.layout.fragment_git_repo_detail) {

    private val binding by viewBinding(FragmentGitRepoDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
    }

    private fun getArgs() {
        arguments?.let { args ->
            val repo: GitRepository? = args.getParcelable("repo")
            repo?.let {
                with(it) {
                    setUpLayout(
                        owner?.avatar_url,
                        owner?.login,
                        name,
                        description,
                        stargazers_count,
                        forks_count
                    )
                }
            }
        }
    }
    private fun setUpLayout(
        mAvatarUrl: String?,
        mOwnerName: String?,
        mRepoName: String?,
        mRepoDesc: String?,
        mRepoStars: String?,
        mRepoForks: String?
    ) {

        with(binding) {
            repoDesc.text = getString(R.string.title_desc,mRepoDesc)
            repoName.text = getString(R.string.title_name,mRepoName)
            repoStars.text = getString(R.string.title_stars,mRepoStars)
            repoForks.text = getString(R.string.title_forks,mRepoForks)
            ownerName.text = mOwnerName
            mAvatarUrl?.let { ownerAvatar.loadFromUrl(it) }
        }

    }

}