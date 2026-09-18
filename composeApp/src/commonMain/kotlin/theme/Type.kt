package theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.quattrocento_sans_bold
import jasontoms.composeapp.generated.resources.quattrocento_sans_bold_italic
import jasontoms.composeapp.generated.resources.quattrocento_sans_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

data class AppFont(
    val resource: FontResource,
    val weight: FontWeight,
    val style: FontStyle = FontStyle.Normal,
)

/**
 * This list is shared with the web entry point, which preloads exactly these fonts before the
 * first frame so the page never flashes the fallback font.
 */
val appFonts: List<AppFont> = listOf(
    AppFont(Res.font.quattrocento_sans_regular, FontWeight.Normal),
    AppFont(Res.font.quattrocento_sans_bold, FontWeight.Bold),
    AppFont(Res.font.quattrocento_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
)

@Composable
fun appFontFamily(): FontFamily = FontFamily(appFonts.map { Font(it.resource, it.weight, it.style) })

/**
 * The default Material type scale in the site font, with heavier, tighter headings. Big text that
 * is bold and slightly condensed reads as a lot more "website" than the stock Material weights.
 */
@Composable
fun appTypography(): Typography {
    val fontFamily = appFontFamily()
    return remember(fontFamily) {
        val base = Typography()
        fun TextStyle.inAppFont() = copy(fontFamily = fontFamily)
        Typography(
            displayLarge = base.displayLarge.copy(fontWeight = FontWeight.Bold, letterSpacing = (-0.02).em).inAppFont(),
            displayMedium = base.displayMedium.copy(fontWeight = FontWeight.Bold, letterSpacing = (-0.02).em).inAppFont(),
            displaySmall = base.displaySmall.copy(fontWeight = FontWeight.Bold, letterSpacing = (-0.015).em).inAppFont(),
            headlineLarge = base.headlineLarge.copy(fontWeight = FontWeight.Bold, letterSpacing = (-0.01).em).inAppFont(),
            headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.Bold, letterSpacing = (-0.01).em).inAppFont(),
            headlineSmall = base.headlineSmall.copy(fontWeight = FontWeight.Bold).inAppFont(),
            titleLarge = base.titleLarge.copy(fontWeight = FontWeight.Bold).inAppFont(),
            titleMedium = base.titleMedium.copy(fontWeight = FontWeight.Bold).inAppFont(),
            titleSmall = base.titleSmall.inAppFont(),
            bodyLarge = base.bodyLarge.copy(lineHeight = base.bodyLarge.fontSize * 1.6f).inAppFont(),
            bodyMedium = base.bodyMedium.copy(lineHeight = base.bodyMedium.fontSize * 1.6f).inAppFont(),
            bodySmall = base.bodySmall.inAppFont(),
            labelLarge = base.labelLarge.copy(fontWeight = FontWeight.Bold).inAppFont(),
            labelMedium = base.labelMedium.inAppFont(),
            labelSmall = base.labelSmall.inAppFont(),
        )
    }
}
