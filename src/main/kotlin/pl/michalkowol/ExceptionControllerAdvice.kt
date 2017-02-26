package pl.michalkowol

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class ExceptionControllerAdvice {

    private val log = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    fun handleException(ex: Exception): Error {
        log.error("", ex)
        return IntenralError(message = ex.message)
    }

    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(ArithmeticException::class, NullPointerException::class)
    fun handleTwoExceptions(ex: Exception): Error {
        log.error("", ex)
        return IntenralError(status = HttpStatus.PAYMENT_REQUIRED.value(), message = ex.message)
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException): Error {
        log.info("Not found: {}", ex)
        return NotFound(message = ex.msg)
    }
}
