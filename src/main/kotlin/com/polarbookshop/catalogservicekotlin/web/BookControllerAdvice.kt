package com.polarbookshop.catalogservicekotlin.web

import com.polarbookshop.catalogservicekotlin.domain.BookAlreadyExistsException
import com.polarbookshop.catalogservicekotlin.domain.BookNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bookNotFoundHandler(ex: BookNotFoundException) : String {
        return ex.message ?: "Book not found."
    }

    @ExceptionHandler(BookAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun bookAlreadyExistsHandler(ex: BookAlreadyExistsException) : String {
        return ex.message ?: "Book already exists."
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException) : Map<String, String> {
        return ex.bindingResult.allErrors
            .filterIsInstance<FieldError>()
            .associate { fieldError ->
                fieldError.field to (fieldError.defaultMessage ?: "Invalid value.")
            }
    }
}
