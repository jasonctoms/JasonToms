import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import kotlinx.browser.document
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont
import theme.appFonts
import kotlin.time.Duration.Companion.milliseconds

private const val FONT_LOAD_TIMEOUT_MS = 4_000L

/**
 * True once every site font is loaded, so the first frame is already in the right font instead
 * of flashing the Roboto fallback that is built into Skiko. If the fonts are slow or fail, it
 * turns true anyway after [FONT_LOAD_TIMEOUT_MS]; the text then swaps font whenever they arrive.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberFontsReady(): Boolean {
    val fonts = appFonts.map { preloadFont(it.resource, it.weight, it.style) }
    var timedOut by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(FONT_LOAD_TIMEOUT_MS.milliseconds)
        timedOut = true
    }
    return timedOut || fonts.all { it.value != null }
}

/**
 * Tells the loading splash in index.html that the first real frame has been drawn, so it can
 * fade out.
 */
@Composable
fun SignalAppReady() {
    LaunchedEffect(Unit) {
        withFrameNanos { }
        document.documentElement?.classList?.add("app-ready")
    }
}
