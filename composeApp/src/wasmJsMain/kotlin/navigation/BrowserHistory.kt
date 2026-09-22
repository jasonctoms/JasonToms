package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import blog.BlogPosts
import kotlinx.browser.document
import kotlinx.browser.window

/**
 * Path-based routing (`jasontoms.com/blog/2026-09-21`). The host serves `index.html` for any path
 * that isn't a real file (see `wrangler.jsonc`), and the app works out the page from the path.
 */
fun currentBrowserRoute(): Route = Route.fromPath(window.location.pathname)

/**
 * Keeps the [navigator] and the browser in sync in both directions:
 * - navigating in the app pushes a browser history entry and updates the tab title
 * - the browser's back/forward buttons (or an edited URL) navigate the app
 */
@OptIn(ExperimentalWasmJsInterop::class)
@Composable
fun BrowserHistoryEffect(navigator: Navigator) {
    DisposableEffect(navigator) {
        window.onpopstate = { navigator.navigateTo(currentBrowserRoute()) }
        onDispose { window.onpopstate = null }
    }

    val route = navigator.currentRoute
    LaunchedEffect(route) {
        val url = "/${route.path}"
        when {
            currentBrowserRoute() != route -> window.history.pushState(null, "", url)
            // the page is right but the URL isn't in its canonical form, e.g. a blog post that
            // doesn't exist and fell back to the newest one
            window.location.pathname != url -> window.history.replaceState(null, "", url)
        }
        document.title = route.documentTitle
    }
}

private val Route.documentTitle: String
    get() = when (this) {
        Route.Home -> "Jason Toms — App Developer & Engineering Manager"
        Route.Portfolio -> "Portfolio — Jason Toms"
        is Route.Blog -> BlogPosts.find(date)?.let { "${it.title} — Jason Toms" } ?: "Blog — Jason Toms"
    }
