package com.michalkowol

import java.io.PrintWriter
import java.io.StringWriter
import java.net.HttpURLConnection.*
import java.util.*

internal interface ServerError {
    val status: Int
    val code: String
    val id: UUID
    val message: String?
}

internal class NotFound(
    override val message: String,
    override val status: Int = HTTP_NOT_FOUND,
    override val code: String = "NF",
    override val id: UUID = UUID.randomUUID()
) : ServerError

internal class InternalServerError(
    override val message: String?,
    val stackTrace: String? = null,
    override val status: Int = HTTP_INTERNAL_ERROR,
    override val code: String = "IE",
    override val id: UUID = UUID.randomUUID()
) : ServerError {

    companion object {
        fun create(ex: Exception): InternalServerError {
            return InternalServerError(ex.message, Errors.extractStackTrace(ex))
        }
    }
}

internal class BadRequest(
    override val message: String,
    override val status: Int = HTTP_BAD_REQUEST,
    override val code: String = "BR",
    override val id: UUID = UUID.randomUUID()
) : ServerError

internal class NotFoundException(override val message: String, cause: Throwable? = null) : RuntimeException(message, cause)

internal class BadRequestException(override val message: String, cause: Throwable? = null) : RuntimeException(message, cause)

private object Errors {
    internal fun extractStackTrace(throwable: Throwable): String {
        val errorMsgWriter = StringWriter()
        throwable.printStackTrace(PrintWriter(errorMsgWriter))
        return errorMsgWriter.toString()
    }
}
