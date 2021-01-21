package com.gabriel.kotlinstars.core.data.remote

import com.gabriel.kotlinstars.core.data.ApiGithub
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitRepositoriesRemote {

    fun fetchPublicRepositories(): Flow<List<GitRepository>?> {
        return flow {
            try {
                val repos = ApiGithub.apiService.fetchRepos()
                emit(repos)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(null)
            }
        }
    }
}