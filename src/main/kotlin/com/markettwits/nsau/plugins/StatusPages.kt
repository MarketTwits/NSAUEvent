package com.markettwits.nsau.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

fun Application.configureStatusPages() {

        install(StatusPages) {
            status(HttpStatusCode.NotFound) { call, status ->
                val errorResponse = ErrorResponse(
                    statusCode = status.value,
                    timestamp = Clock.System.now().toString(),
                    path = call.request.uri,
                    message = "Cannot ${call.request.httpMethod.value} ${call.request.uri}"
                )
                call.respond(HttpStatusCode.NotFound, errorResponse)
            }

            exception<Throwable> { call, cause ->
                val errorResponse = ErrorResponse(
                    statusCode = HttpStatusCode.InternalServerError.value,
                    timestamp = Clock.System.now().toString(),
                    path = call.request.uri,
                    message = cause.localizedMessage ?: "Internal Server Error"
                )
                call.respond(HttpStatusCode.InternalServerError, errorResponse)
            }
        }
}
@Serializable
data class ErrorResponse(
    val statusCode: Int,
    val timestamp: String,
    val path: String,
    val message: String
)