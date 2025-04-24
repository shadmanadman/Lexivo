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
import org.kmp.playground.lexivo.review.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.review.route.reviewSchema
import kotlin.test.assertEquals


class ReviewSchemaMutationTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                reviewSchema
                packages = SUPPORTED_PACKAGES
                mutations = listOf(
                    ReviewMutations()
                )
            }
        }
    }

    @Test
    fun `when a review is deleted with a valid id, then it should return deleted id`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { deleteReview(id: \"1\") }"
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
    fun `when a review is updated with a valid id, then it should return updated review`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { updateReview(id: \"1\", name: "test") }"
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
    fun `when a new review is added, then it should return the new review`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { insertReview(id: \"1\", name: "test") }"
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