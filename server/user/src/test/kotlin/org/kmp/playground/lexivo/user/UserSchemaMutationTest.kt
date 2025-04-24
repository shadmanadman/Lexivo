package org.kmp.playground.lexivo.user

import com.expediagroup.graphql.server.ktor.GraphQL
import com.mongodb.assertions.Assertions.assertTrue
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.junit.Test
import org.kmp.playground.lexivo.user.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.user.route.UserMutations
import org.kmp.playground.lexivo.user.route.UserQuery
import org.kmp.playground.lexivo.user.route.userSchema
import kotlin.test.assertEquals

class UserSchemaMutationTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                userSchema
                packages = SUPPORTED_PACKAGES
                mutations = listOf(
                    UserMutations()
                )
            }
        }
    }


    @Test
    fun `when a user is deleted with a valid id, then it should return deleted id`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { deleteUser(id: \"1\") }"
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
    fun `when a user is updated with a valid id, then it should return updated user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { updateUser(id: \"1\", name: "test") }"
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
    fun `when a new user is added, then it should return the new user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { insertUser(id: \"1\", name: "test") }"
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
    fun `when a new user is added by Email, then it should return the new user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { registerUserByEmail(id: \"1\", name: "test") }"
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
    fun `when a new user is added by OAuth, then it should return the new user`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { registerUserByOAuth(id: \"1\", name: "test") }"
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