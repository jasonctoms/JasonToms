package ui.projects

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.picky_background
import jasontoms.composeapp.generated.resources.project_1_description
import jasontoms.composeapp.generated.resources.project_1_title
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import theme.pickyOrange
import theme.pickyPurple
import ui.portfolio.AppIcon
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.PortfolioCard
import utils.CdnImage

@Composable
fun Picky(modifier: Modifier = Modifier) {
    PortfolioCard(
        modifier = modifier,
        accent = BrandAccent(pickyPurple, pickyOrange),
        title = stringResource(Res.string.project_1_title),
        media = { size -> AppIcon(icon = CdnImage.PICKY_ICON, size = size) },
        backgroundImage = Res.drawable.picky_background,
        links = {
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.AppStore(AppForStoreLink.PICKY))
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.Website("https://picky.ink"))
        },
    ) {
        CardBodyText(stringResource(Res.string.project_1_description))
    }
}

@Previews
@Composable
private fun PickyPreview() {
    ContentPreview {
        Picky()
    }
}
