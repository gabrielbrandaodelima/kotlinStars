package com.gabriel.kotlinstars.core.di

import com.gabriel.kotlinstars.features.ui.view.GitRepoDetailFragment
import com.gabriel.kotlinstars.features.ui.view.GitReposFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class, ViewModelModule::class])
interface Component {

    fun inject(gitReposFragment: GitReposFragment)

    fun inject(gitRepoDetailFragment: GitRepoDetailFragment)

}