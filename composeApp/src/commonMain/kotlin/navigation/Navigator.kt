package navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class Navigator(startRoute: Route = Route.Home) {
    val backStack: SnapshotStateList<Route> = mutableStateListOf<Route>().apply {
        addAll(stackFor(startRoute))
    }

    val currentRoute: Route get() = backStack.last()

    fun navigateTo(route: Route) {
        if (route == currentRoute) return
        while (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
        if (route != Route.Home) {
            backStack.add(route)
        }
    }

    fun goBack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    private fun stackFor(route: Route): List<Route> =
        if (route == Route.Home) listOf(Route.Home) else listOf(Route.Home, route)
}
