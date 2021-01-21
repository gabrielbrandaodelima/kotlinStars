package com.gabriel.kotlinstars.core.domain.usecase

import com.gabriel.kotlinstars.core.data.repository.GitReposRepository
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchReposUseCase @Inject constructor(
    private val repository: GitReposRepository
) {

    suspend fun execute(): Flow<List<GitRepository>?> {
        return repository.fetchRepos()
    }

}