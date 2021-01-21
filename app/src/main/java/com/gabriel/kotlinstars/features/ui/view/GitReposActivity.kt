package com.gabriel.kotlinstars.features.ui.view

import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.gabriel.kotlinstars.R
import com.gabriel.kotlinstars.core.extensions.viewBinding
import com.gabriel.kotlinstars.core.platform.BaseActivity
import com.gabriel.kotlinstars.databinding.ActivityGitReposBinding

class GitReposActivity : BaseActivity(R.layout.activity_git_repos) {

    private val binding by viewBinding(ActivityGitReposBinding::inflate)

    override fun onDestinationChangedListener(destination: NavDestination) {
        super.onDestinationChangedListener(destination)

    }

}