import oddlyspaced.dribbble.scraper.scraper.FeedScraper
import org.jsoup.Jsoup
import java.net.URL

class Scrape {

    fun main() {
        //getCategories()
        //parsePosts()
        //parseSinglePost()
        // FeedScraper().parsePosts()
    }

    private fun getCategories() {
        val response = URL("https://dribbble.com/").readText()
        val doc = Jsoup.parse(response)
        // finding category root element
        val categories = doc.getElementsByAttributeValue("data-param", "category")
        println(categories.size)
        for (category in categories) {
            println(category.text())
        }
    }

}