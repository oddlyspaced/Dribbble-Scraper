import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL

class FeedScraper {

    fun parsePosts() {
        val response = URL("https://dribbble.com/search/wallpaper").readText()
        val doc = Jsoup.parse(response)
        // finding all items
        val items = doc.getElementsByClass("shot-thumbnail js-shot-thumbnail shot-thumbnail-container     ")
        println(items.size)
        for (item in items) {
            getPostTitle(item)
            //getThumbnail(item)
            //getPostCreator(item)
            //getPostCreatorThumbnail(item)
            //getLikesCount(item)
            //getCommentsCount(item)
            getPostLink(item)
            println("----------")
        }
    }

    private fun getPostTitle(element: Element) {
        val postTitleItems = element.getElementsByClass("shot-title")
        println(postTitleItems[0].text())
    }

    private fun getThumbnail(element: Element) {
        val thumbnailNode = element.getElementsByClass("shot-thumbnail-placeholder")
        val img = thumbnailNode[0].getElementsByTag("img")
        val link = img[0].attr("src")
        println(link)
    }

    private fun getPostCreator(element: Element) {
        val displayNameItems = element.getElementsByClass("display-name")
        println(displayNameItems[0].text())
    }

    private fun getPostCreatorThumbnail(element: Element) {
        val creatorItems = element.getElementsByClass("hoverable url")
        val creatorThumbItems = creatorItems[0].getElementsByClass("photo")
        println(creatorThumbItems[0].attr("src"))
    }

    private fun getLikesCount(element: Element) {
        val countItems = element.getElementsByClass("js-shot-likes-count")
        println(countItems[0].text())
    }

    private fun getCommentsCount(element: Element) {
        val commentItems = element.getElementsByClass("js-shot-comments-count")
        println(commentItems[0].text())
    }

    private fun getPostLink(element: Element) {
        val likesItems = element.getElementsByClass("shot-thumbnail-link dribbble-link js-shot-thumbnail-link")
        if (likesItems.isEmpty()) {
            val likeItemsNew = element.getElementsByClass("js-overlay-scroll-to-section")
            println(likeItemsNew[0].attr("href"))
            return
        }
        println(likesItems[0].attr("href"))
    }

}