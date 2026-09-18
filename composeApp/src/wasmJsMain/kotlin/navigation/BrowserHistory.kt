package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import kotlinx.browser.document
import kotlinx.browser.window

/**
 * Hash-based routing (`jasontoms.com/#/portfolio`). GitHub Pages only serves `index.html` from the
 * root, so a hash keeps every page deep-linkable without needing any server-side rewrites.
 */
fun currentBrowserRoute(): Route = Route.fromPath(window.location.hash.removePrefix("#"))

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
        window.onhashchange = { navigator.navigateTo(currentBrowserRoute()) }
        onDispose {
            window.onpopstate = null
            window.onhashchange = null
        }
    }

    val route = navigator.currentRoute
    LaunchedEffect(route) {
        if (currentBrowserRoute() != route) {
            val url = if (route.path.isEmpty()) window.location.pathname else "#/${route.path}"
            window.history.pushState(null, "", url)
        }
        document.title = route.documentTitle
    }
}

private val Route.documentTitle: String
    get() = when (this) {
        Route.Home -> "Jason Toms — App Developer & Engineering Manager"
        Route.Portfolio -> "Portfolio — Jason Toms"
    }
