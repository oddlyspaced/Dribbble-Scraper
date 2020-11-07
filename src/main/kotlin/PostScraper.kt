import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL

class PostScraper {
    private fun parseSinglePost() {
        val response = URL("https://dribbble.com/shots/14475349-Custom-Kitchen-Design-Ideas").readText()
        val doc = Jsoup.parse(response)
        parseUserInfo(doc)
        parseMedia(doc)
        parseText(doc)
        parseCategories(doc)
        parseUploadDate(doc)
        parseStats(doc)
        //parseCommentList(doc)
    }

    private fun parseUserInfo(element: Element) {
        val userContainer = element.getElementsByClass("shot-user-container")
        val titleItems = userContainer[0].getElementsByClass("shot-header-title")
        val title = titleItems[0].text()
        println(title)

        val userTitleItems = userContainer[0].getElementsByClass("hoverable shot-user-link")
        val userTitle = userTitleItems[0].text()
        println(userTitle)

        val userThumbnail = userContainer[0].getElementsByClass("hoverable url")
        val thumbnailLink = userThumbnail[0].getElementsByTag("source")[0].attr("srcset")
        println(thumbnailLink)
    }

    private fun parseMedia(element: Element) {
        val mediaContentRoot = element.getElementsByClass("media-content")
        val mediaItems = mediaContentRoot[0].getElementsByTag("picture")
        val imgItems = mediaItems[0].getElementsByTag("img")
        println(imgItems[0].attr("data-src"))
    }

    private fun parseText(element: Element) {
        val descContainer = element.getElementsByClass("shot-description-container ")[0]
        val descItems = descContainer.getElementsByTag("p")
        for (item in descItems) {
            println(item.text())
        }
    }

    private fun parseCategories(element: Element) {
        val categoryContainer = element.getElementsByClass("shot-tags-container js-shot-tags-container")[0]
        val tags = categoryContainer.getElementsByClass("shot-tag")
        for (tag in tags) {
            println(tag.text())
            println(tag.attr("href"))
        }
    }

    private fun parseUploadDate(element: Element) {
        val uploadDateItem = element.getElementsByClass("shot-uploaded-date")
        println(uploadDateItem.text())
    }

    private fun parseStats(element: Element) {
        val statItems = element.getElementsByClass("shot-stat-item")
        for (item in statItems) {
            println(item.text())
        }
    }

    private fun parseCommentList(element: Element) {
        val commentItems = element.getElementsByClass("shot-comments-list")
        println("size : ${commentItems.size}")
        for (comment in commentItems) {
            parseCommentItem(comment)
        }
    }

    private fun parseCommentItem(element: Element) {
        val author = element.getElementsByClass("shot-comment-author")[0].text()
        println(author)
    }

    // comments : https://dribbble.com/shots/14475349/comments?page=1&sort=recent&format=json
}