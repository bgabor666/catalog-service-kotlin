package com.polarbookshop.catalogservicekotlin.domain

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val price: Double,
)
