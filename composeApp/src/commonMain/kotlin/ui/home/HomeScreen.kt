package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.soapbox_title
import navigation.Route
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.Dimens
import theme.Previews
import theme.sectionSpacing
import ui.Section
import ui.WebsiteSection
import ui.footer.Footer
import ui.personal.Intro
import ui.personal.Soapbox

@Composable
fun HomeScreen(onNavigate: (Route) -> Unit, modifier: Modifier = Modifier) {
    val explorePortfolio = { onNavigate(Route.Portfolio) }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(sectionSpacing()),
        contentPadding = PaddingValues(top = Dimens.medium),
    ) {
        item { HomeHero(onExplorePortfolio = explorePortfolio) }
        item { AboutSection(onExplorePortfolio = explorePortfolio) }
        item { Section(section = WebsiteSection.PERSONAL) { Intro() } }
        item { Section(title = stringResource(Res.string.soapbox_title)) { Soapbox() } }
        item { Footer(onNavigate = onNavigate) }
    }
}

@Previews
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(onNavigate = {})
    }
}
