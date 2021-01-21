package com.gabriel.kotlinstars.core.di

import com.gabriel.kotlinstars.core.data.remote.GitRepositoriesRemote
import com.gabriel.kotlinstars.core.data.repository.GitReposRepository
import com.gabriel.kotlinstars.core.data.repository.GitReposRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun providesGitReposRepository(): GitReposRepository {
        return GitReposRepositoryImp(providesGitReposRemote())
    }

    @Provides
    fun providesGitReposRemote(): GitRepositoriesRemote {
        return GitRepositoriesRemote()
    }

}