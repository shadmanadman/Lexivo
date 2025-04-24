package org.kmp.playground.lexivo.user

import com.expediagroup.graphql.server.ktor.GraphQL
import com.mongodb.assertions.Assertions.assertTrue
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.application.install
import io.ktor.server.testing.testApplication
import org.kmp.playground.lexivo.user.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.user.route.UserQuery
import org.kmp.playground.lexivo.user.route.userSchema
import kotlin.test.Test
import kotlin.test.assertEquals


class UserSchemaQueryTest() {

    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                userSchema
                packages = SUPPORTED_PACKAGES
                queries = listOf(
                    UserQuery()
                )
            }
        }
    }

    @Test
    fun `when userById is called with a valid id, then it should return a user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { userById(id: \"1\") { name email } }"
                }
                """.trimIndent()
            )
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val body = response.bodyAsText()
        println(body)
        assertTrue(body.contains("name"))
    }


    @Test
    fun `when userById is called with a non-existent id, then it should throw an exception`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { userById(id: \"-1\") { name email } }"
                }
                """.trimIndent()
            )
        }

        assertEquals(HttpStatusCode.NotFound, response.status)
        val body = response.bodyAsText()
        println(body)
        assertTrue(body.contains("name"))
    }


    @Test
    fun `when users is called, then it should return a list of user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { users() { name email } }"
                }
                """.trimIndent()
            )
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val body = response.bodyAsText()
        println(body)
        assertTrue(body.contains("name"))
    }
}