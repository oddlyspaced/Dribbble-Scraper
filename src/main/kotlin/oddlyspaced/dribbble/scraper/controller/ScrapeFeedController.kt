package oddlyspaced.dribbble.scraper.controller

import oddlyspaced.dribbble.scraper.scraper.FeedScraper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ScrapeFeedController {

    val dribbbleBaseUrl = "https://dribbble.com/"
    val scraper = FeedScraper()

    /**
     * TODO :- Add 'Made With' and 'Downloads' Support
     */

    private fun generateLink(baseLink: String, tags: HashMap<String, String>): String {
        var baseLink = baseLink
        if (tags.isNotEmpty())
            baseLink = "$baseLink?"
        tags.forEach { (key, value) ->
            if (value.isNotEmpty())
                baseLink = "$baseLink$key=$value&"
        }
        println(baseLink.substring(0, baseLink.length - 1))
        return baseLink.substring(0, baseLink.length - 1)
    }

    @GetMapping("/popularToday")
    fun popularToday(
        @RequestParam(value = "tag", defaultValue = "") tag: String,
        @RequestParam(value = "color", defaultValue = "") color: String,
        @RequestParam(value = "timeframe", defaultValue = "") timeframe: String,
        @RequestParam(value = "page", defaultValue = "1") page: String
    ) = scraper.parsePosts(
        generateLink(
            dribbbleBaseUrl,
            hashMapOf(
                "tag" to tag,
                "color" to color,
                "timeframe" to timeframe,
                "page" to page,
            )
        )
    )

    @GetMapping("/search")
    fun search(
        @RequestParam(value = "query") query: String,
        @RequestParam(value = "page", defaultValue = "1") page: Int
    ) = scraper.parsePosts("$dribbbleBaseUrl/search/$query")

    @GetMapping("/popularCategory")
    fun category(
        @RequestParam(value = "category") category: String,
        @RequestParam(value = "page", defaultValue = "1") page: Int
    ) = scraper.parsePosts("$dribbbleBaseUrl/shots/popular/$category")

}