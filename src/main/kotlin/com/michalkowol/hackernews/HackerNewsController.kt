package com.michalkowol.hackernews

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.inject.Inject

@RestController
@EnableAutoConfiguration
@RequestMapping(path = arrayOf("/news"), produces = arrayOf("application/json"))
internal class HackerNewsController @Inject constructor(private val hackerNewsService: HackerNewsService) {

    @RequestMapping
    fun topStory(): HackerNews {
        return hackerNewsService.topStory()
    }
}
