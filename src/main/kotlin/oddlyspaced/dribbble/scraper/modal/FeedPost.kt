package oddlyspaced.dribbble.scraper.modal

data class FeedPost(
    val title: String,
    val link: String,
    val thumbnail: String,
    val author: String,
    val authorThumbnail: String,
    val authorLink: String,
    val badge: String,
    val likes: Int,
    val comments: Int
)