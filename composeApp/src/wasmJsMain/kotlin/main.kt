import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import navigation.BrowserHistoryEffect
import navigation.Navigator
import navigation.currentBrowserRoute

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val viewport = document.getElementById("composeApp") ?: error("Missing #composeApp element")
    ComposeViewport(viewport) {
        val navigator = remember { Navigator(startRoute = currentBrowserRoute()) }
        BrowserHistoryEffect(navigator)
        // the loading splash stays up until this renders, so the wait for fonts is hidden behind it
        if (rememberFontsReady()) {
            App(navigator)
            SignalAppReady()
        }
    }
}
