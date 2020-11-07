package oddlyspaced.dribbble.scraper.scraper

import oddlyspaced.dribbble.scraper.modal.FeedPost
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL

class FeedScraper {

    val dribbbleBaseUrl = "https://dribbble.com/"

    fun parsePosts(link: String, page: Int): ArrayList<FeedPost> {
        val response = URL("$link?page=$page").readText()
        val doc = Jsoup.parse(response)
        // finding all items
        val items = doc.getElementsByClass("shot-thumbnail js-shot-thumbnail shot-thumbnail-container     ")
        val posts = arrayListOf<FeedPost>()
        for (item in items) {
            posts.add(
                FeedPost(
                    getPostTitle(item),
                    getPostLink(item),
                    getPostThumbnail(item),
                    getPostCreator(item),
                    getPostCreatorThumbnail(item),
                    getPostCreatorLink(item),
                    getPostBadge(item),
                    getLikesCount(item),
                    getCommentsCount(item),
                )
            )
        }
        return posts
    }

    private fun getPostTitle(element: Element): String {
        val postTitleItems = element.getElementsByClass("shot-title")
        return postTitleItems[0].text()
    }

    private fun getPostThumbnail(element: Element): String {
        val thumbnailNode = element.getElementsByClass("shot-thumbnail-placeholder")
        val img = thumbnailNode[0].getElementsByTag("img")
        return img[0].attr("src")
    }

    private fun getPostCreator(element: Element): String {
        val displayNameItems = element.getElementsByClass("display-name")
        return displayNameItems[0].text()
    }

    private fun getPostCreatorThumbnail(element: Element): String {
        val creatorItems = element.getElementsByClass("hoverable url")
        val creatorThumbItems = creatorItems[0].getElementsByClass("photo")
        return creatorThumbItems[0].attr("src")
    }

    private fun getLikesCount(element: Element): Int {
        val countItems = element.getElementsByClass("js-shot-likes-count")
        return (countItems[0].text()).toInt()
    }

    private fun getCommentsCount(element: Element): Int {
        val commentItems = element.getElementsByClass("js-shot-comments-count")
        return (commentItems[0].text()).toInt()
    }

    private fun getPostLink(element: Element): String {
        val likesItems = element.getElementsByClass("shot-thumbnail-link dribbble-link js-shot-thumbnail-link")
        if (likesItems.isEmpty()) {
            val likeItemsNew = element.getElementsByClass("js-overlay-scroll-to-section")
            return likeItemsNew[0].attr("href")
        }
        return likesItems[0].attr("href")
    }

    private fun getPostBadge(element: Element): String {
        var badgeItems = element.getElementsByClass("badge badge-pro")
        if (badgeItems.isEmpty()) {
            badgeItems = element.getElementsByClass("badge badge-team")
        }
        return if (badgeItems.isEmpty()) "" else badgeItems[0].text()
    }

    private fun getPostCreatorLink(element: Element): String {
        val creatorItems = element.getElementsByClass("hoverable url")[0]
        return creatorItems.attr("href")
    }

}