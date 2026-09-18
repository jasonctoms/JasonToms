package navigation

sealed interface Route {
    val path: String

    data object Home : Route {
        override val path = ""
    }

    data object Portfolio : Route {
        override val path = "portfolio"
    }

    companion object {
        private val all = listOf(Home, Portfolio)

        /** Unknown paths fall back to [Home] rather than showing an error page. */
        fun fromPath(path: String): Route {
            val normalized = path.trim().trim('/').lowercase()
            return all.firstOrNull { it.path == normalized } ?: Home
        }
    }
}
