package com.nestor.spacex.repository.launches

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.nestor.spacex.LaunchesListQuery
import com.nestor.spacex.remote.Response
import com.nestor.spacex.util.toResponse
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(private val client: ApolloClient) :
    LaunchesRepository {
    override suspend fun getLaunches(cursor: String?): Response<LaunchesListQuery.Data> =
        client.query(LaunchesListQuery(cursor = Optional.presentIfNotNull(cursor))).toResponse()
}