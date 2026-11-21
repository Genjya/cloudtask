import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun healthReturnsUp() = testApplication {
        application { module() }

        val response = client.get("/health")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(ContentType.Application.Json, response.contentType()?.withoutParameters())
        assertEquals("""{"status":"UP"}""", response.bodyAsText())
    }
}
