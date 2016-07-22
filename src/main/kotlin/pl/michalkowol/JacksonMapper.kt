package pl.michalkowol

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Component

@Component
open class JacksonMapper {

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val  builder = Jackson2ObjectMapperBuilder()
        builder.modulesToInstall(Jdk8Module(), KotlinModule())
        return builder
    }
}