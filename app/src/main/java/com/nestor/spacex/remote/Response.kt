package com.nestor.spacex.remote

import com.apollographql.apollo3.api.Operation

/**
 * This class is a wrapper around a graphql query in order to show loading or error states in the ui.
 */

enum class ErrorType {
    NETWORK_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

open class Response<T : Operation.Data>
class Loading<T : Operation.Data> : Response<T>()
class Success<T : Operation.Data>(val data: T) : Response<T>()
class Error<T : Operation.Data>(val cause: ErrorType) : Response<T>()
