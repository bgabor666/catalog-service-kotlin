package com.polarbookshop.catalogservicekotlin.domain

class BookAlreadyExistsException(isbn: String) : RuntimeException(
    "A book with ISBN $isbn already exists."
)
