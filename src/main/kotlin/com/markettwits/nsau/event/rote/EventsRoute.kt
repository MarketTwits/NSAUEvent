package com.markettwits.nsau.event.rote

import com.markettwits.nsau.event.controller.EventControllerImpl
import com.markettwits.nsau.event.datastore.FakeEventDataStore
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val eventStore = FakeEventDataStore

private val controller = EventControllerImpl(eventStore)


fun Route.configureEventRoutes() {

    get("/events") {

        val limit = call.request.queryParameters["limit"]?.toIntOrNull()
        val offset = call.request.queryParameters["offset"]?.toIntOrNull() ?: 0
        val name = call.request.queryParameters["name"] ?: ""

        controller.getEvents(limit, offset, name).fold(
            onSuccess = {
                call.respond(HttpStatusCode.OK, it)
            }, onFailure = {
                call.respond(it)
            })
    }


    get("/events/name/{name}") {
        val name = call.parameters["name"]
        controller.getByName(name ?: "").fold(
            onSuccess = {
                call.respond(HttpStatusCode.OK, it)
            }, onFailure = {
                call.respond(it)
            })
    }

    get("/events/{id}") {
        val eventId = call.parameters["id"]?.toIntOrNull()
        controller.getById(eventId ?: 0).fold(
            onSuccess = {
                call.respond(HttpStatusCode.OK, it)
            }, onFailure = {
                call.respond(it)
            })
    }


}


