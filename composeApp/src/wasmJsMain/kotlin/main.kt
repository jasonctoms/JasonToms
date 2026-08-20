import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val viewport = document.getElementById("composeApp") ?: error("Missing #composeApp element")
    ComposeViewport(viewport) {
        App()
    }
}
