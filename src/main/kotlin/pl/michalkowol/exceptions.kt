package pl.michalkowol

import java.util.*

interface Error {
    val status: Int
    val level: String
    val code: String
    val id: UUID
    val message: String?
}

data class IntenralError(
    override val status: Int = 500,
    override val level: String = "INFO",
    override val code: String = "IE",
    override val id: UUID = UUID.randomUUID(),
    override val message: String?
) : Error

data class NotFound(
    override val status: Int = 404,
    override val level: String = "INFO",
    override val code: String = "NF",
    override val id: UUID = UUID.randomUUID(),
    override val message: String
) : Error

class NotFoundException(val msg: String) : RuntimeException(msg)
