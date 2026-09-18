package theme

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {
    /** 4dp */
    val xxSmall = 4.dp
    /** 8dp */
    val xSmall = 8.dp
    /** 16dp */
    val small = 16.dp
    /** 24dp */
    val medium = 24.dp
    /** 32dp */
    val large = 32.dp
    /** 64dp */
    val extraLarge = 64.dp

    /** The widest any page content gets, so wide monitors don't stretch everything edge to edge. */
    val maxContentWidth = 1200.dp
    /** Comfortable line length for long-form text. */
    val maxReadingWidth = 760.dp
    /** Height of the site's navigation bar. */
    val topBarHeight = 64.dp
}

/** Horizontal page padding, which grows with the window so content doesn't hug the edges on larger screens. */
@Composable
fun pageGutter(): Dp = when (LocalWindowSizeClass.current.widthSizeClass) {
    WindowWidthSizeClass.Compact -> Dimens.small
    WindowWidthSizeClass.Medium -> Dimens.medium
    else -> Dimens.large
}

/** Space between the major sections of a page. */
@Composable
fun sectionSpacing(): Dp = when (LocalWindowSizeClass.current.widthSizeClass) {
    WindowWidthSizeClass.Compact -> Dimens.large
    else -> Dimens.extraLarge
}
