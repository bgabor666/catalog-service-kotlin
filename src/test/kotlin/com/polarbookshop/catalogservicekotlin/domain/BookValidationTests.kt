package com.polarbookshop.catalogservicekotlin.domain

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BookValidationTests {

    companion object {
        private lateinit var validator: Validator

        @JvmStatic
        @BeforeAll
        fun setup() {
            val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
            validator = factory.validator
        }
    }

    @Test
    fun `when all fields correct then validation succeeds`() {
        val book = Book(
            isbn = "1234567890",
            title = "Title",
            author = "author",
            price = 9.90,
        )

        val violations: Set<ConstraintViolation<Book>> = validator.validate(book)
        assertTrue(violations.isEmpty())
    }

    @Test
    fun `when ISBN is defined but incorrect then validation fails`() {
        val book = Book(
            isbn = "a234567890",
            title = "Title",
            author = "Author",
            price = 9.90,
        )

        val violations: Set<ConstraintViolation<Book>> = validator.validate(book)
        assertEquals(1, violations.size)

        val violationMessage = violations.iterator().next().message
        assertEquals("The ISBN format must be valid.", violationMessage)
    }
}
