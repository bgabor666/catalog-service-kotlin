package com.polarbookshop.catalogservicekotlin

import com.polarbookshop.catalogservicekotlin.domain.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceKotlinApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `when post request then book created`() {
        val expectedBook = Book(
            isbn = "1231231231",
            title = "Title",
            author = "Author",
            price = 9.90,
        )

        webTestClient
            .post()
            .uri("/books")
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated
            .expectBody(Book::class.java)
            .value { actualBook ->
                assertNotNull(actualBook)
                assertEquals(expectedBook.isbn, actualBook.isbn)
            }
    }
}
