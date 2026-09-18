package ui.projects

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.breez_background
import jasontoms.composeapp.generated.resources.project_3_description
import jasontoms.composeapp.generated.resources.project_3_title
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.breezDarkTeal
import theme.breezTeal
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import ui.portfolio.AppIcon
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.PortfolioCard
import utils.CdnImage

@Composable
fun Breez(modifier: Modifier = Modifier) {
    PortfolioCard(
        modifier = modifier,
        accent = BrandAccent(breezTeal, breezDarkTeal),
        title = stringResource(Res.string.project_3_title),
        media = { size -> AppIcon(icon = CdnImage.BREEZ_ICON, size = size) },
        backgroundImage = Res.drawable.breez_background,
        links = {
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.AppStore(AppForStoreLink.BREEZ))
            LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.Website("https://breez.life"))
        },
    ) {
        CardBodyText(stringResource(Res.string.project_3_description))
    }
}

@Previews
@Composable
private fun BreezPreview() {
    ContentPreview {
        Breez()
    }
}
