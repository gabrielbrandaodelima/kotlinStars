package com.gabriel.kotlinstars.core.domain.model.response

import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository

data class GitRepositoriesResponse(
    val repositories: List<GitRepository>
)