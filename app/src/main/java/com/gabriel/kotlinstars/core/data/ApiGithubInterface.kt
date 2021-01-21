package com.gabriel.kotlinstars.core.data

import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import retrofit2.http.GET

interface ApiGithubInterface {

    @GET("repositories")
    suspend fun fetchRepos(): List<GitRepository>?

}