package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val windowSizeClass = remember(windowInfo.containerSize, density) {
        getWindowSizeClass(containerSize = windowInfo.containerSize, density = density)
    }
    if (windowSizeClass != null) {
        CompositionLocalProvider(LocalWindowSizeClass provides windowSizeClass) {
            MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme) {
                Box(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun getWindowSizeClass(containerSize: IntSize, density: Density): WindowSizeClass? {
    if (containerSize.width <= 0 || containerSize.height <= 0) {
        return null
    }
    val dpSize = with(density) {
        DpSize(
            width = containerSize.width.toDp(),
            height = containerSize.height.toDp()
        )
    }
    val windowSizeClass = WindowSizeClass.calculateFromSize(dpSize)
    println("Window Size Class: $windowSizeClass, Dp Size: $dpSize")
    return windowSizeClass
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val LocalWindowSizeClass = compositionLocalOf<WindowSizeClass> {
    error("LocalWindowSizeClass not initialized")
}
