package ui.school

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.school_degree
import jasontoms.composeapp.generated.resources.school_description
import jasontoms.composeapp.generated.resources.school_location
import jasontoms.composeapp.generated.resources.school_name
import jasontoms.composeapp.generated.resources.school_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import theme.components.SelectableText
import theme.lsuGold
import theme.lsuPurple
import ui.Section
import ui.WebsiteSection
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.CardPhotoStrip
import ui.portfolio.Logo
import ui.portfolio.LogoTile
import ui.portfolio.PortfolioCard
import ui.portfolio.WORDMARK_ASPECT_RATIO
import utils.CdnImage

@Composable
fun SchoolContent() {
    Section(section = WebsiteSection.SCHOOL) {
        PortfolioCard(
            accent = BrandAccent(lsuPurple, lsuGold),
            title = stringResource(Res.string.school_name),
            subtitle = stringResource(Res.string.school_location),
            period = stringResource(Res.string.school_years),
            media = { size ->
                LogoTile(logo = Logo(CdnImage.LSU_LOGO, Color.White), size = size, aspectRatio = WORDMARK_ASPECT_RATIO)
            },
        ) {
            SelectableText(
                text = stringResource(Res.string.school_degree),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            CardBodyText(stringResource(Res.string.school_description))
            CardPhotoStrip(
                photos = listOf(CdnImage.ME_AT_LSU, CdnImage.BREADBOARD, CdnImage.OSCILLISCOPE, CdnImage.MICROMIXER),
                modifier = Modifier.padding(top = Dimens.xSmall),
            )
        }
    }
}

@Composable
@Previews
private fun SchoolContentPreview() {
    ContentPreview {
        SchoolContent()
    }
}
