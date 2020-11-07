package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ScrapeFeedController {
    @GetMapping("/popularToday")
    fun popularToday(@RequestParam(value = "page", defaultValue = "1") name: Int) =
        Greeting("Hello, $name")

    fun Greeting(tt: String): String {
        return tt;
    }
}