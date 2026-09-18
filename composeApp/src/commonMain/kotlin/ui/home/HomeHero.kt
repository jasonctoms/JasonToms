package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.hero_cta
import jasontoms.composeapp.generated.resources.hero_title
import jasontoms.composeapp.generated.resources.hero_tagline
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.ContentColumn
import theme.components.SelectableText
import theme.components.SocialLinks
import ui.components.BigButton
import utils.CdnImage

/**
 * The top of the home page: a banner photo with the profile photo overlapping its bottom edge,
 * followed by name, tagline, and the main call to action to go look at the portfolio.
 */
@Composable
fun HomeHero(onExplorePortfolio: () -> Unit, modifier: Modifier = Modifier) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    val bannerHeight = when (widthClass) {
        WindowWidthSizeClass.Compact -> 160.dp
        WindowWidthSizeClass.Medium -> 220.dp
        else -> 280.dp
    }
    val photoSize = if (widthClass == WindowWidthSizeClass.Compact) 128.dp else 168.dp

    ContentColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Dimens.small)) {
        Box(modifier = Modifier.fillMaxWidth().padding(bottom = photoSize / 2)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bannerHeight)
                    .clip(MaterialTheme.shapes.extraLarge),
                model = CdnImage.SOGNEFJORD.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            ProfilePhoto(
                size = photoSize,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = Dimens.medium, y = photoSize / 2),
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
            SelectableText(
                text = stringResource(Res.string.hero_title).uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            SelectableText(
                text = "Jason Toms",
                style = if (widthClass == WindowWidthSizeClass.Compact) {
                    MaterialTheme.typography.displayMedium
                } else {
                    MaterialTheme.typography.displayLarge
                },
                color = MaterialTheme.colorScheme.onBackground,
            )
            SelectableText(
                modifier = Modifier.widthIn(max = Dimens.maxReadingWidth),
                text = stringResource(Res.string.hero_tagline),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = null),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        FlowRow(
            modifier = Modifier.padding(top = Dimens.xSmall),
            horizontalArrangement = Arrangement.spacedBy(Dimens.small),
            verticalArrangement = Arrangement.spacedBy(Dimens.xSmall),
            itemVerticalAlignment = Alignment.CenterVertically,
        ) {
            BigButton(
                text = stringResource(Res.string.hero_cta),
                onClick = onExplorePortfolio,
            )
            SocialLinks()
        }
    }
}

@Previews
@Composable
private fun HomeHeroPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            HomeHero(onExplorePortfolio = {})
        }
    }
}
