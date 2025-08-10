package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val windowSizeClass = getWindowSizeClass()
    CompositionLocalProvider(LocalWindowSizeClass provides windowSizeClass) {
        MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun getWindowSizeClass(): WindowSizeClass {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val dpSize = remember(windowInfo.containerSize, density) {
        with(density) {
            DpSize(
                width = windowInfo.containerSize.width.toDp(),
                height = windowInfo.containerSize.height.toDp()
            )
        }
    }
    return WindowSizeClass.calculateFromSize(dpSize)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val LocalWindowSizeClass = compositionLocalOf<WindowSizeClass> {
    error("LocalWindowSizeClass not initialized")
}
