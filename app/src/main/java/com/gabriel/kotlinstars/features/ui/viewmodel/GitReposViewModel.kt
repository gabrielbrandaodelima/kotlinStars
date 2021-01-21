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
): BaseViewModel() {

    private val _gitRepos = MutableLiveData<List<GitRepository>>()
    val gitRepos: LiveData<List<GitRepository>> = _gitRepos

    private val _loading = MutableLiveData(false)
    var loading : LiveData<Boolean> = _loading

    private val coroutinesContext = Dispatchers.IO

    fun fetchRepos(page: Int = 1) {
        _loading.postValue(true)
        viewModelScope.launch(coroutinesContext) {
            gitReposUseCase.execute(page.toString())
                .catch { e ->
                    handleFailure(e.message)
                    _loading.postValue(false)
                }
                .collect { repos ->
                    _gitRepos.postValue(repos)
                    _loading.postValue(false)
                }
        }
    }

}