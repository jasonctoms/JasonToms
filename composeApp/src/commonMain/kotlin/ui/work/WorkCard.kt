package ui.work

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_company_info
import jasontoms.composeapp.generated.resources.work_my_part
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import theme.components.StoreBadgeStyle
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.CardLabel
import ui.portfolio.CardPhotoStrip
import ui.portfolio.Logo
import ui.portfolio.LogoTiles
import ui.portfolio.PortfolioCard
import ui.portfolio.WORDMARK_ASPECT_RATIO
import utils.CdnImage

/**
 * @param secondaryLogo an optional second logo, usually the company's best-known product
 * @param photos optional photos from the job, shown as thumbnails under the text
 */
@Composable
fun WorkCard(
    title: String,
    location: String,
    years: String,
    aiDescription: String,
    myPart: String,
    logo: Logo,
    accent: BrandAccent,
    websiteUrl: String,
    appForStoreLink: AppForStoreLink?,
    modifier: Modifier = Modifier,
    secondaryLogo: Logo? = null,
    photos: List<CdnImage> = emptyList(),
) {
    PortfolioCard(
        modifier = modifier,
        accent = accent,
        title = title,
        subtitle = location,
        period = years,
        media = { size ->
            LogoTiles(
                *listOfNotNull(logo, secondaryLogo).toTypedArray(),
                size = size,
                aspectRatio = WORDMARK_ASPECT_RATIO,
            )
        },
        links = {
            appForStoreLink?.let {
                LinkBadge(modifier = Modifier.height(44.dp), type = LinkBadgeType.PlayStore(it))
            }
            LinkBadge(
                modifier = Modifier.height(44.dp),
                // work cards pair the website with Google Play badges, so match those
                type = LinkBadgeType.Website(websiteUrl, sizedLike = StoreBadgeStyle.PLAY_STORE),
            )
        },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(Dimens.xxSmall)) {
            CardLabel(stringResource(Res.string.work_my_part))
            CardBodyText(myPart)
        }
        Column(verticalArrangement = Arrangement.spacedBy(Dimens.xxSmall)) {
            CardLabel(stringResource(Res.string.work_company_info))
            CardBodyText(aiDescription)
        }
        if (photos.isNotEmpty()) {
            CardPhotoStrip(photos = photos, modifier = Modifier.padding(top = Dimens.xSmall))
        }
    }
}

@Previews
@Composable
private fun WorkCardPreview() {
    ContentPreview {
        WorkCard(
            title = "Job Title",
            location = "Location, USA",
            years = "August 2020 - September 2024",
            aiDescription = "This is a description of the job role, responsibilities, and achievements.",
            myPart = "Key tasks and projects handled during the tenure.",
            logo = Logo(CdnImage.PICKY_ICON, Color.White),
            accent = BrandAccent(Color(0xFF6750A4), Color(0xFFE8A33D)),
            websiteUrl = "https://example.com",
            appForStoreLink = AppForStoreLink.BANKID,
        )
    }
}
