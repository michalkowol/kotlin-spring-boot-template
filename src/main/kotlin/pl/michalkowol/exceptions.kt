package pl.michalkowol

import java.util.*

data class NotFound(
        val status: Int = 404,
        val level: String = "INFO",
        val code: String = "NF",
        val id: String = UUID.randomUUID().toString(),
        val message: String = "Not found."
)

class NotFoundException(val msg: String) : RuntimeException(msg)
class NameException(val msg: String) : RuntimeException(msg)
