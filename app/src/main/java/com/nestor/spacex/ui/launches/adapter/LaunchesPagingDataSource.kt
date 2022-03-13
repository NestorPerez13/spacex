package com.nestor.spacex.ui.launches.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nestor.spacex.LaunchesListQuery
import com.nestor.spacex.remote.Success
import com.nestor.spacex.repository.launches.LaunchesRepository

class LaunchesPagingDataSource(private val repository: LaunchesRepository) :
    PagingSource<String, LaunchesListQuery.Launch>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, LaunchesListQuery.Launch> {
        return try {
            val response = repository.getLaunches(cursor = params.key)
            if (response is Success) {
                LoadResult.Page(
                    data = response.data.launches.launches.map { it!! },
                    nextKey = if (response.data.launches.hasMore) response.data.launches.cursor else null,
                    prevKey = null
                )
            } else {
                throw Exception("Server error response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, LaunchesListQuery.Launch>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.nextKey
        }
    }
}