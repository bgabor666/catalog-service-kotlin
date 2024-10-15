package com.polarbookshop.catalogservicekotlin.web

import com.polarbookshop.catalogservicekotlin.domain.BookNotFoundException
import com.polarbookshop.catalogservicekotlin.domain.BookService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(BookController::class)
class BookControllerMvcTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var bookService: BookService

    @Test
    fun `when get book not exists then should return 404`() {
        val isbn = "73737313940"

        given(bookService.viewBookDetails(isbn))
            .willThrow(BookNotFoundException::class.java)

        mockMvc.get("/books/$isbn")
            .andExpect {
                status { isNotFound() }
            }
    }
}
