package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.example.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {

        application {

            configureRouting(TestRepository())


        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("I'm working", bodyAsText())
        }
        client.post("/add?name=Oleh")
        client.post("/add?name=Oleh")
        client.post("/add?name=Oleh")
        client.post("/add?name=Nazar")
        client.post("/add?name=Nazar")
        client.get("/all").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("[{\"name\":\"Oleh\",\"wins\":3},{\"name\":\"Nazar\",\"wins\":2}]", bodyAsText())
        }
    }
}
