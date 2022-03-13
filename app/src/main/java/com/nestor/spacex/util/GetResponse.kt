package com.nestor.spacex.util

import android.util.Log
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException
import com.nestor.spacex.remote.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Error

private const val TAG = "GetResponse"
suspend fun <T : Operation.Data> ApolloCall<T>.toResponse(): Response<T> {
    return try {
        val result = this.execute()
        if (result.hasErrors()) {
            Log.e(TAG, "toResponse: ${result.errors}")
            Error<T>(cause = ErrorType.SERVER_ERROR)
        }
        result.data?.let {
            return Success(data = it)
        } ?: kotlin.run {
            Log.e(TAG, "toResponse: data is null")
            return Error(cause = ErrorType.UNKNOWN_ERROR)
        }
    } catch (e: ApolloException) {
        Log.e(TAG, "toResponse: ", e)
        Error(cause = ErrorType.NETWORK_ERROR)
    }
}
