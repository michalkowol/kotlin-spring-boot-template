package com.michalkowol

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.client.RestTemplate

@Configuration
internal open class AppConfiguration {

    @Bean
    open fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val  builder = Jackson2ObjectMapperBuilder()
        builder.modulesToInstall(KotlinModule())
        return builder
    }
}
