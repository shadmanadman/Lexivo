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
import org.kmp.playground.lexivo.notification.route.NotificationMutations
import org.kmp.playground.lexivo.notification.route.NotificationQuery
import org.kmp.playground.lexivo.notification.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.notification.route.notificationSchema
import kotlin.test.assertEquals


class NotificationSchemaQueryTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                notificationSchema
                packages = SUPPORTED_PACKAGES
                queries = listOf(
                    NotificationQuery()
                )
            }
        }
    }



    @Test
    fun `when notificationById is called with a valid id, then it should return a notification`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { notificationById(id: \"1\") { name email } }"
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
    fun `when notificationByRecipientId is called with a valid id, then it should return a list of notification`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { notificationRecipientId(id: \"1\") { name email } }"
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