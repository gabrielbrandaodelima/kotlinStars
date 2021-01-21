package com.gabriel.kotlinstars.core.data.repository

import com.gabriel.kotlinstars.core.data.remote.GitRepositoriesRemote
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GitReposRepository {
    suspend fun fetchRepos(page: String): Flow<List<GitRepository>?>
}

class GitReposRepositoryImp @Inject constructor(
    private val remote: GitRepositoriesRemote
) : GitReposRepository {

    override suspend fun fetchRepos(page: String): Flow<List<GitRepository>?> {
        return remote.fetchPublicRepositories(page)
    }

}