package ui

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import navigation.Navigator
import navigation.Route
import theme.AppTheme
import theme.Previews
import theme.components.WebsiteBackground
import ui.home.HomeScreen
import ui.navigation.SiteTopBar
import ui.portfolio.PortfolioScreen

@Composable
fun SiteScaffold(navigator: Navigator) {
    Box(modifier = Modifier.fillMaxSize()) {
        WebsiteBackground()
        Column(modifier = Modifier.fillMaxSize()) {
            SiteTopBar(currentRoute = navigator.currentRoute, onNavigate = navigator::navigateTo)
            NavDisplay(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                backStack = navigator.backStack,
                onBack = navigator::goBack,
                transitionSpec = { pageTransition() },
                popTransitionSpec = { pageTransition() },
                predictivePopTransitionSpec = { pageTransition() },
                entryProvider = entryProvider {
                    entry<Route.Home> { HomeScreen(onNavigate = navigator::navigateTo) }
                    entry<Route.Portfolio> { PortfolioScreen(onNavigate = navigator::navigateTo) }
                },
            )
        }
    }
}

/** New pages fade in while drifting up slightly, which feels like a page load rather than an app push. */
private fun pageTransition(): ContentTransform =
    (fadeIn(tween(durationMillis = 350, delayMillis = 90)) +
        slideInVertically(tween(durationMillis = 350, delayMillis = 90)) { height -> height / 40 })
        .togetherWith(fadeOut(tween(durationMillis = 90)))

@Previews
@Composable
private fun SiteScaffoldPreview() {
    AppTheme {
        SiteScaffold(navigator = Navigator())
    }
}
