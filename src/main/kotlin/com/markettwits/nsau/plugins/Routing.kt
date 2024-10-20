package com.markettwits.nsau.plugins

import com.markettwits.nsau.event.rote.configureEventRoutes
import com.markettwits.nsau.hashtag.rote.configureHashTagRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        configureEventRoutes()
        configureHashTagRoutes()
    }
}