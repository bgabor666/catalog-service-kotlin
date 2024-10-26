package com.polarbookshop.catalogservicekotlin.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.Instant

data class Book(
    @Id
    val id: Long? = null,

    @field:NotBlank(message = "The book ISBN must be defined.")
    @field:Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})",
        message = "The ISBN format must be valid."
    )
    val isbn: String,

    @field:NotBlank(message = "The book title must be defined.")
    val title: String,

    @field:NotBlank(message = "The book author must be defined.")
    val author: String,

    @field:NotNull(message = "The book price must be defined.")
    @field:Positive(message = "The book price must be greater than zero.")
    val price: Double,

    @CreatedDate
    val createdDate: Instant? = null,

    @LastModifiedDate
    val lastModifiedDate: Instant? = null,

    @Version
    val version: Int = 0,
)

/*
data class BookRequest(
    @field:NotBlank(message = "The book ISBN must be defined.")
    @field:Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})$",
        message = "The ISBN format must be valid."
    )
    val isbn: String?,

    @field:NotBlank(message = "The book title must be defined.")
    val title: String?,

    @field:NotBlank(message = "The book author must be defined.")
    val author: String?,

    @field:NotNull(message = "The book price must be defined.")
    @field:Positive(message = "The book price must be greater than zero.")
    val price: Double?
)

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val price: Double
)

fun BookRequest.toBook(): Book {
    return Book(
        isbn = requireNotNull(this.isbn) { "ISBN must not be null" },
        title = requireNotNull(this.title) { "Title must not be null" },
        author = requireNotNull(this.author) { "Author must not be null" },
        price = requireNotNull(this.price) { "Price must not be null" }
    )
}

*/
