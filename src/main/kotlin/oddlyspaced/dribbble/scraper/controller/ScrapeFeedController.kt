package oddlyspaced.dribbble.scraper.controller

import oddlyspaced.dribbble.scraper.scraper.FeedScraper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ScrapeFeedController {

    val dribbbleBaseUrl = "https://dribbble.com/"
    val scraper = FeedScraper()

    @GetMapping("/popularToday")
    fun popularToday(
        @RequestParam(value = "page", defaultValue = "1") page: Int
    ) = scraper.parsePosts(dribbbleBaseUrl, page)

    @GetMapping("/search")
    fun search(
        @RequestParam(value = "query") query: String,
        @RequestParam(value = "page", defaultValue = "1") page: Int
    ) = scraper.parsePosts("$dribbbleBaseUrl/search/$query", page)

}