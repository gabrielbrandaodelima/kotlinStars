package com.gabriel.kotlinstars.core.domain.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitRepositoryResponse(
    @SerializedName("total_count") val total_count: Int? = null,
    @SerializedName("incomplete_results") val incomplete_results: Boolean? = null,
    @SerializedName("items") val items: List<GitRepository>? = null,
) : Parcelable

@Parcelize
data class GitRepository(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("full_name") val full_name: String? = null,
    @SerializedName("owner") val owner: Owner? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("stargazers_count") val stargazers_count: String? = null,
    @SerializedName("forks_count") val forks_count: String? = null,
) : Parcelable