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
import org.kmp.playground.lexivo.notification.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.notification.route.notificationSchema
import kotlin.test.assertEquals


class NotificationSchemaMutationTest {
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                notificationSchema
                packages = SUPPORTED_PACKAGES
                mutations = listOf(
                    NotificationMutations()
                )
            }
        }
    }

    @Test
    fun `when a notification is deleted with a valid id, then it should return deleted id`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { deleteNotification(id: \"1\") }"
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
    fun `when a notification is updated with a valid id, then it should return updated notification`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { updateNotification(id: \"1\", name: "test") }"
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
    fun `when a new notification is added, then it should return the new notification`() = testApplication {
        application {
            setupApplicationWithGraphQL()
        }


        val response = client.post("/graphql") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                    "query": "query { insertNotification(id: \"1\", name: "test") }"
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