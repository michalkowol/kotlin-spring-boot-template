package com.michalkowol

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
internal class ExceptionControllerAdvice {

    private val log = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handleUnknownException(ex: Exception): ServerError {
        log.error(ex.message, ex)
        return InternalServerError.create(ex)
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException): ServerError {
        log.info(ex.message, ex)
        return NotFound(ex.message)
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun handleBadRequestException(ex: BadRequestException): ServerError {
        log.info(ex.message, ex)
        return BadRequest(ex.message)
    }
}
