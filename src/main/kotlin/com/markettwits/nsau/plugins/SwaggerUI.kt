package com.markettwits.nsau.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

/**
 * @suppress
 * add after support ktor 3.0.0 in
 * https://github.com/SMILEY4/ktor-swagger-ui/pull/140
 */
fun Application.configureSwaggerUI() {

    //TODO add after support

    routing {
        openAPI(path="openapi", swaggerFile = "openapi/documentation.yaml")
    }
}