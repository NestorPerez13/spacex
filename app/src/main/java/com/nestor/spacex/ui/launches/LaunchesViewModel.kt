package com.nestor.spacex.ui.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nestor.spacex.repository.dispatcher_provider.DispatcherProvider
import com.nestor.spacex.repository.launches.LaunchesRepository
import com.nestor.spacex.ui.launches.adapter.LaunchesPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(launchesRepository: LaunchesRepository) : ViewModel() {
    val launches = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { LaunchesPagingDataSource(repository = launchesRepository) })
        .flow
        .cachedIn(viewModelScope)
}