package oddlyspaced.dribbble.scraper.controller

import oddlyspaced.dribbble.scraper.scraper.MiscScrape
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MiscController {

    val misc = MiscScrape()

    @GetMapping("/categories")
    fun categories() = misc.getCategories()

}