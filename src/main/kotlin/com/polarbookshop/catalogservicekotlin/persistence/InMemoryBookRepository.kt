package com.polarbookshop.catalogservicekotlin.persistence

import com.polarbookshop.catalogservicekotlin.domain.Book
import com.polarbookshop.catalogservicekotlin.domain.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository : BookRepository {
    companion object {
        private val books = ConcurrentHashMap<String, Book>()
    }

    override fun findAll(): Iterable<Book> {
        return books.values
    }

    override fun findByIsbn(isbn: String): Book? {
        /*
        return if (existsByIsbn(isbn)) {
            books[isbn]
        } else {
            null
        }
         */

        return books[isbn]?.takeIf { existsByIsbn(isbn) }
    }

    override fun existsByIsbn(isbn: String): Boolean {
        // return books[isbn] != null
        return books.containsKey(isbn)
    }

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String) {
        books.remove(isbn)
    }
}