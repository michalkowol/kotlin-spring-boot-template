package com.michalkowol.hackernews

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.inject.Inject

@Service
internal class HackerNewsService @Inject constructor(private val restTemplate: RestTemplate) {

    private fun topStories(): List<Int> {
        return restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/topstories.json", Array<Int>::class.java).toList()
    }

    private fun storyById(id: Int): HackerNews {
        return restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/$id.json", HackerNews::class.java)
    }

    fun topStory(): HackerNews {
        val topStoryId = topStories().first()
        return storyById(topStoryId)
    }
}
