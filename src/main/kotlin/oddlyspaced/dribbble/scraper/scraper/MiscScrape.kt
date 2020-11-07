package oddlyspaced.dribbble.scraper.scraper

import oddlyspaced.dribbble.scraper.modal.Category
import org.jsoup.Jsoup
import java.net.URL

class MiscScrape {

    fun getCategories(): ArrayList<Category> {
        val response = URL("https://dribbble.com/").readText()
        val doc = Jsoup.parse(response)
        // finding category root element
        val categoriesItems = doc.getElementsByAttributeValue("data-param", "category")
        val categories = arrayListOf<Category>()
        for (category in categoriesItems) {
            categories.add(
                Category(
                    category.text(),
                    category.attr("href")
                )
            )
        }
        return categories
    }

}