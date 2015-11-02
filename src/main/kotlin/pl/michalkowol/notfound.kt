package pl.michalkowol

data class NotFound(
        val status: Int = 404,
        val level: String = "INFO",
        val code: String = "NF",
        val id: Long = System.currentTimeMillis(),
        val message: String = "Not found."
)
class NotFoundException(val msg: String? = null) : RuntimeException(msg)