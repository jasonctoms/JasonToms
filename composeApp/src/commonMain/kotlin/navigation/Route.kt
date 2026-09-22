package navigation

import blog.BlogPosts
import kotlinx.datetime.LocalDate

sealed interface Route {
    val path: String

    data object Home : Route {
        override val path = ""
    }

    data object Portfolio : Route {
        override val path = "portfolio"
    }

    /** @param date the post being read, or null when there are no posts yet. */
    data class Blog(val date: LocalDate?) : Route {
        override val path = if (date == null) BLOG_PATH else "$BLOG_PATH/$date"
    }

    companion object {
        private const val BLOG_PATH = "blog"

        /**
         * Unknown paths fall back to [Home], and unknown blog posts to the newest post, rather than
         * showing an error page.
         */
        fun fromPath(path: String): Route {
            val segments = path.trim().trim('/').lowercase().split('/')
            return when (segments.first()) {
                Portfolio.path -> Portfolio
                BLOG_PATH -> Blog(BlogPosts.resolve(segments.getOrNull(1))?.date)
                else -> Home
            }
        }
    }
}
