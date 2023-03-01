package com.example.plugins

import com.example.repository.Repository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(db: Repository) {
    routing {
        get("/") {
            call.respondText("I'm working")
        }
        get("/all") {
            val records = db.allRecord()
            call.respond(records)
        }
        post("/add") {
//            val recordsParameters = call.receive<Parameters>()
//            val name = recordsParameters["name"]
//                ?: return@post call.respond(
//                    HttpStatusCode.BadRequest, "Missing Name"
//                )
//            val wins = recordsParameters["wins"]
//            val record = db.addRecord(name, wins?.toInt() ?: 0)
//            record ?: return@post call.respond(
//                HttpStatusCode.BadRequest, "Couldn't get a record"
//            )
//            call.respond(record)

            val name = call.request.queryParameters["name"] ?: return@post call.respond(
                HttpStatusCode.BadRequest, "Couldn't get a name"
            )
            var record = db.allRecord().find { it.name == name }
            record =
                if (record != null) db.addRecord(record.name, record.wins + 1)
                else db.addRecord(name, 1)
            record ?: return@post call.respond(
                HttpStatusCode.BadRequest, "Couldn't get a record"
            )
            call.respond(record)
        }
    }
}
