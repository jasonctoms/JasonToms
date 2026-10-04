package ui.projects

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.project_4_description
import jasontoms.composeapp.generated.resources.project_4_title
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import theme.helpMyCityBlue
import theme.helpMyCityConeOrange
import ui.portfolio.AppIcon
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.PortfolioCard
import utils.CdnImage

@Composable
fun HelpMyCity(modifier: Modifier = Modifier) {
    PortfolioCard(
        modifier = modifier,
        accent = BrandAccent(helpMyCityBlue, helpMyCityConeOrange),
        title = stringResource(Res.string.project_4_title),
        media = { size -> AppIcon(icon = CdnImage.HELP_MY_CITY_ICON, size = size) },
        links = {
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.GitHub("https://github.com/jasonctoms/HelpMyCity"))
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.Website("https://helpmycity.dev"))
        },
    ) {
        CardBodyText(stringResource(Res.string.project_4_description))
    }
}

@Previews
@Composable
private fun HelpMyCityPreview() {
    ContentPreview {
        HelpMyCity()
    }
}
