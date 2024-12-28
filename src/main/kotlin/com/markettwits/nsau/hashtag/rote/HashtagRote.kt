package com.markettwits.nsau.hashtag.rote

import com.markettwits.nsau.hashtag.controller.HashTagControllerImpl
import com.markettwits.nsau.hashtag.datastore.FakeHashTagsDataStore
import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val hashTagStore = FakeHashTagsDataStore

private val hashTagController = HashTagControllerImpl(hashTagStore)

fun Route.configureHashTagRoutes() {

    route("/hashtag/") {

        route("test") {
            get({
                operationId = "findPets"
                description = "Returns all pets from the system that the user has access to"
                request {
                    queryParameter<Int>("limit") {
                        description = "maximum number of results to return"
                        required = false
                    }
                    queryParameter<Int>("offset") {
                        description = "skiping"
                        required = false
                    }
                }
            }) {
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



            get(path = "{id}", builder = {
                operationId = "findPets"
                description = "Returns all pets from the system that the user has access to"
                request {
                    queryParameter<Int>("id") {
                        description = "hashtag id"
                        required = true
                    }
                }
            }){
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

        }
    }