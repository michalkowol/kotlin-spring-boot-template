package com.michalkowol

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@EnableAutoConfiguration
internal class RedirectController {

    @RequestMapping("/redirect")
    fun redirectToHealth(): String {
        return "redirect:/health"
    }
}
