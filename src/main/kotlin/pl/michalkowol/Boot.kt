package pl.michalkowol

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Boot {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Boot::class.java, *args)
        }
    }
}
