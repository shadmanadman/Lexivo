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
import org.kmp.playground.lexivo.feedback.route.FeedbackQuery
import org.kmp.playground.lexivo.feedback.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.feedback.route.feedbackSchema
import kotlin.test.assertEquals

class FeedbackSchemaQueryTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                feedbackSchema
                packages = SUPPORTED_PACKAGES
                queries = listOf(
                    FeedbackQuery()
                )
            }
        }
    }



    @Test
    fun `when feedbackById is called with a valid id, then it should return a feedback`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { feedbackById(id: \"1\") { name email } }"
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
    fun `when feedbackByUserId is called with a valid id, then it should return a list of feedback`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { feedbackByUserId(id: \"1\") { name email } }"
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
    fun `when feedbackByReviewId is called with a valid id, then it should return a list of feedback`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { feedbackByReviewId(id: \"1\") { name email } }"
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