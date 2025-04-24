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
import org.kmp.playground.lexivo.team.route.SUPPORTED_PACKAGES
import org.kmp.playground.lexivo.team.route.TeamQuery
import org.kmp.playground.lexivo.team.route.teamSchema
import kotlin.test.assertEquals

class TeamSchemaQueryTest{
    private fun setupApplicationWithGraphQL() = testApplication {
        install(GraphQL) {
            schema {
                teamSchema
                packages = SUPPORTED_PACKAGES
                queries = listOf(
                    TeamQuery()
                )
            }
        }
    }

    @Test
    fun `when teamById is called with a valid id, then it should return a team`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { teamById(id: \"1\") { name email } }"
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
    fun `when teamByOwnerId is called with a valid id, then it should return a team`() =
        testApplication {
            application {
                setupApplicationWithGraphQL()
            }


            val response = client.post("/graphql") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                {
                    "query": "query { teamByOwnerId(id: \"-1\") { name email } }"
                }
                """.trimIndent()
                )
            }

            assertEquals(HttpStatusCode.NotFound, response.status)
            val body = response.bodyAsText()
            println(body)
            assertTrue(body.contains("name"))
        }
}