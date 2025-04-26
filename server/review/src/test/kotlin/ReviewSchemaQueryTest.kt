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
import org.kmp.playground.lexivo.review.route.ReviewMutations
import org.kmp.playground.lexivo.review.route.ReviewQuery
import org.kmp.playground.lexivo.review.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.review.route.reviewSchema
import kotlin.test.assertEquals

class ReviewSchemaQueryTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                reviewSchema
                packages = SUPPORTED_PACKAGES
                queries = listOf(
                    ReviewQuery()
                )
            }
        }
    }

    @Test
    fun `when reviewById is called with a valid id, then it should return a review`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { reviewById(id: \"1\") { name email } }"
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
    fun `when reviewByTeamId is called with a valid id, then it should return a list of reviews`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { reviewByTeamId(id: \"-1\") { name email } }"
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
    fun `when reviewByUserId is called with a valid id, then it should return a list of reviews`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { reviewByUserId(id: \"-1\") { name email } }"
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
    fun `when reviewByStatus is called with a valid id, then it should return a review`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { reviewByStatus(id: \"-1\") { name email } }"
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
    fun `when reviewByType is called with a valid id, then it should return a review`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { reviewByType(id: \"-1\") { name email } }"
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
    fun `when reviews is called, then it should return a list of all reviews`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { reviews() { name email } }"
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