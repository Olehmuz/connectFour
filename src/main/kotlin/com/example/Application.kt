package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.repository.DatabaseFactory
import com.example.repository.RecordsRepository
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(CORS) {
        allowHost("client-host")
        allowHost("client-host:8081")
        allowHost("client-host:5500")
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowHeaders)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowCredentials = true
        allowSameOrigin = true
        allowNonSimpleContentTypes = true
        allowHost("localhost:5500")
        allowHost("localhost:8081")
        allowHost("localhost:8080")
        anyHost()
    }
    configureSerialization()
    DatabaseFactory.init()
    val db = RecordsRepository()
    configureRouting(db)
}
