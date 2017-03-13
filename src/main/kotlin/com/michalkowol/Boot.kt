package com.michalkowol

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*

@SpringBootApplication
open class Boot {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val profile = Optional.ofNullable(System.getProperty("environment")).orElse("default")
            val springApplication = SpringApplication(Boot::class.java)
            springApplication.setAdditionalProfiles(profile)
            springApplication.run(*args)
        }
    }
}
