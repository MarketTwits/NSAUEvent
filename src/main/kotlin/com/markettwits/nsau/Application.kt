package com.markettwits.nsau

import com.markettwits.nsau.plugins.configureJson
import com.markettwits.nsau.plugins.configureRouting
import com.markettwits.nsau.plugins.configureStatusPages
import com.markettwits.nsau.plugins.configureSwaggerUI
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.cio.EngineMain.main(args)

fun Application.module() {
    configureStatusPages()
    configureJson()
    configureSwaggerUI()
    configureRouting()
}
