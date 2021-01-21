package com.gabriel.kotlinstars.features.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabriel.kotlinstars.core.domain.model.entity.GitRepository
import com.gabriel.kotlinstars.core.domain.usecase.FetchReposUseCase
import com.gabriel.kotlinstars.core.domain.usecase.FetchReposUseCase_Factory
import com.gabriel.kotlinstars.core.platform.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitReposViewModel @Inject constructor(
    private val gitReposUseCase: FetchReposUseCase
) : BaseViewModel() {

    private val _gitRepos = MutableLiveData<Pair<List<GitRepository>?, Int>>()
    val gitRepos: LiveData<Pair<List<GitRepository>?, Int>> = _gitRepos
    var page: Int = 1


    private val coroutinesContext = Dispatchers.IO

    fun fetchRepos() {
        when (page) {
            1 -> _loading.postValue(true)
            else -> _pageLoading.postValue(true)
        }
        viewModelScope.launch(coroutinesContext) {
            gitReposUseCase.execute((page).toString())
                .catch { e ->
                    handleFailure(e.message)
                    when (page) {
                        1 -> _loading.postValue(false)
                        else -> _pageLoading.postValue(false)
                    }
                }
                .collect { repos ->
                    _gitRepos.postValue(Pair(repos, page))
                    when (page++) {
                        1 -> _loading.postValue(false)
                        else -> _pageLoading.postValue(false)
                    }
                }
        }
    }

}