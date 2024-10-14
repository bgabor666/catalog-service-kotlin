package com.polarbookshop.catalogservicekotlin.domain

class BookNotFoundException(isbn: String) : RuntimeException(
    "The book with ISBN $isbn was not found."
)
