package com.michalkowol

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@EnableAutoConfiguration
@RequestMapping(path = arrayOf("/errors"), produces = arrayOf("application/json"))
internal class RadomErrorsController {

    @RequestMapping
    fun randomError(): Map<String, String> {
        val random = Random().nextInt(3)
        if (random == 0) {
            throw IllegalStateException("Random error")
        } else if (random == 1) {
            throw NotFoundException("Not found")
        }
        return mapOf("health" to "ok")
    }
}
