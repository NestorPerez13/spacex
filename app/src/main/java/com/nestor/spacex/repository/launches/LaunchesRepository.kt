package com.nestor.spacex.repository.launches

import com.nestor.spacex.LaunchesListQuery
import com.nestor.spacex.remote.Response

interface LaunchesRepository {
    suspend fun getLaunches(cursor: String? = null): Response<LaunchesListQuery.Data>
}
