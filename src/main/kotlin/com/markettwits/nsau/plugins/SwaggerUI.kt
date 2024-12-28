package com.markettwits.nsau.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.github.smiley4.ktorswaggerui.routing.openApiSpec
import io.github.smiley4.ktorswaggerui.routing.swaggerUI
import io.ktor.server.application.*
import io.ktor.server.routing.*

/**
 * @suppress
 * add after support ktor 3.0.0 in
 * https://github.com/SMILEY4/ktor-swagger-ui/pull/140
 */
fun Application.configureSwaggerUI() {

    install(SwaggerUI) {
        info {
            title = "Swagger NsauEvent"
            version = "1.0.0"
            description = "A sample API that uses a petstore as an example to demonstrate features in the swagger-2.0 specification"
            termsOfService = "http://swagger.io/terms/"
            contact {
                name = "Swagger API Team"
            }
            license {
                name = "MIT"
            }
        }
        examples {
            example("Unexpected Error") {
                value = ErrorModel("Unexpected Error")
            }
        }
    }

    routing {

        route("swagger") {
            swaggerUI("/api.json")
        }
        route("api.json") {
            openApiSpec()
        }
    }
}

private data class ErrorModel(
    val message: String
)