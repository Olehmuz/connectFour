package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.repository.DatabaseFactory
import com.example.repository.RecordsRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    configureSerialization()
    DatabaseFactory.init()
    val db = RecordsRepository()
    configureRouting(db)
}
