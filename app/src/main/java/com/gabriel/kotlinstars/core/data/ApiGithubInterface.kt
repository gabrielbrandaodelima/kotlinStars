package com.gabriel.kotlinstars.core.data

import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiGithubInterface {

    @GET("search/repositories")
    suspend fun fetchRepos(
        @QueryMap queryMap: HashMap<String, Any>? = hashMapOf(
            Pair("q", "language:kotlin"),
            Pair("sort", "stars")
        ),
        @Query("page") page: Int
    ): List<GitRepository>?

}