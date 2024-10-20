package com.markettwits.nsau.hashtag.rote

import com.markettwits.nsau.hashtag.controller.HashTagControllerImpl
import com.markettwits.nsau.hashtag.datastore.FakeHashTagsDataStore
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val hashTagStore = FakeHashTagsDataStore

private val hashTagController = HashTagControllerImpl(hashTagStore)

fun Route.configureHashTagRoutes() {

    get("/hashtags") {
        val limit = call.request.queryParameters["limit"]?.toIntOrNull()
        val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0

        hashTagController.getHashTags(limit, offset).fold(
            onSuccess = {
                call.respond(HttpStatusCode.OK, it)
            }, onFailure = {
                call.respond(HttpStatusCode.InternalServerError, it)
            }
        )
    }

    get("/hashtags/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            hashTagController.getHashTagById(id).fold(
                onSuccess = {
                    call.respond(HttpStatusCode.OK, it)
                }, onFailure = {
                    call.respond(HttpStatusCode.NotFound, it)
                }
            )
        } else {
            call.respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
        }
    }
}