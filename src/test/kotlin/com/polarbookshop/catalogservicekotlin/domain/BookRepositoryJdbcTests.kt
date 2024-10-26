package com.polarbookshop.catalogservicekotlin.domain

import com.polarbookshop.catalogservicekotlin.config.DataConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJdbcTest
@Import(DataConfig::class)
// Disables the embedded database since we want to use Testcontainers
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val jdbcAggregateTemplate: JdbcAggregateTemplate,
) {

    @Test
    fun `find book by ISBN when existing`() {
        // GIVEN
        val bookIsbn = "1234561237"
        val book = Book(
            isbn = bookIsbn,
            title = "Title",
            author = "Author",
            price = 12.90,
        )
        jdbcAggregateTemplate.insert(book)

        // WHEN
        val actualBook = bookRepository.findByIsbn(bookIsbn)

        // THEN
        assertNotNull(actualBook)
        assertEquals(book.isbn, actualBook.isbn)
    }
}
