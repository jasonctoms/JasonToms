package navigation

import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.nav_home
import jasontoms.composeapp.generated.resources.nav_portfolio
import org.jetbrains.compose.resources.StringResource

enum class TopLevelDestination(val route: Route, val label: StringResource) {
    HOME(Route.Home, Res.string.nav_home),
    PORTFOLIO(Route.Portfolio, Res.string.nav_portfolio),
}
