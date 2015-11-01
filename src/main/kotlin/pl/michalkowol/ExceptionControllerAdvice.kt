package pl.michalkowol

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ControllerAdvice
@RestController
class ExceptionControllerAdvice {

    protected val log = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    fun handleException(ex: Exception): Exception {
        log.error("", ex)
        return ex
    }

    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(ArithmeticException::class, NullPointerException::class)
    fun handleTwoExceptions(ex: Exception): Exception {
        log.error("", ex)
        return ex
    }
}
