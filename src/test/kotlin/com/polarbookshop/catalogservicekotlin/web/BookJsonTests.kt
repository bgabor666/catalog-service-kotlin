package com.polarbookshop.catalogservicekotlin.web

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.polarbookshop.catalogservicekotlin.domain.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import kotlin.test.assertEquals

@JsonTest
class BookJsonTests {

    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    fun `test serialize`() {
        val book = Book(
            isbn = "1234567890",
            title = "Title",
            author = "Author",
            price = 9.90,
        )

        val jsonContent = json.write(book).json

        val parsedJson = jacksonObjectMapper().readTree(jsonContent)

        assertEquals(book.isbn, parsedJson.get("isbn").asText())
        assertEquals(book.title, parsedJson.get("title").asText())
        assertEquals(book.author, parsedJson.get("author").asText())
        assertEquals(book.price, parsedJson.get("price").asDouble())
    }

    @Test
    fun `test deserialize`() {
        val content = """
            {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.90
            }
        """.trimIndent()

        val parsedBook = json.parseObject(content)

        assertEquals(
            Book(
                isbn = "1234567890",
                title = "Title",
                author = "Author",
                price = 9.90,
            ),
            parsedBook
        )
    }
}
