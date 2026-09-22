package navigation

import blog.BlogPosts
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.nav_blog
import jasontoms.composeapp.generated.resources.nav_home
import jasontoms.composeapp.generated.resources.nav_portfolio
import org.jetbrains.compose.resources.StringResource

enum class TopLevelDestination(val route: Route, val label: StringResource) {
    HOME(Route.Home, Res.string.nav_home),
    PORTFOLIO(Route.Portfolio, Res.string.nav_portfolio),
    BLOG(Route.Blog(BlogPosts.latest?.date), Res.string.nav_blog);

    /** Whether [route] is this destination or somewhere inside it, like any post on the blog. */
    fun contains(route: Route): Boolean = route::class == this.route::class
}
