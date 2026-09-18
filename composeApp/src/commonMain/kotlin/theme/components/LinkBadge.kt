package theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.app_store_badge
import jasontoms.composeapp.generated.resources.google_play_badge
import jasontoms.composeapp.generated.resources.ic_link
import jasontoms.composeapp.generated.resources.website
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.Dimens

@Composable
fun LinkBadge(type: LinkBadgeType, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    when (type) {
        is LinkBadgeType.AppStore -> StoreBadge(
            modifier = modifier,
            painter = painterResource(Res.drawable.app_store_badge),
            contentDescription = "Download on the App Store",
            onClick = { uriHandler.openUri(type.url) },
        )

        is LinkBadgeType.PlayStore -> StoreBadge(
            modifier = modifier,
            painter = painterResource(Res.drawable.google_play_badge),
            contentDescription = "Get it on Google Play",
            onClick = { uriHandler.openUri(type.url) },
        )

        is LinkBadgeType.Website -> WebsiteBadge(
            modifier = modifier,
            sizedLike = type.sizedLike,
            onClick = { uriHandler.openUri(type.url) },
        )
    }
}

/**
 * The official store badges have to keep their exact proportions: forcing a size that doesn't
 * match their aspect ratio crops off the thin outline at the edges. Callers set either a height or
 * a width, and the other follows from the artwork.
 */
@Composable
private fun StoreBadge(
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(onClick = onClick),
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
    )
}

/**
 * A home-made button in the style of the store badges: black, with the same gray outline, and the
 * exact proportions of the store badge it sits beside so the two line up. Like the text on the real
 * badges, the icon and label scale with the button's height.
 */
@Composable
private fun WebsiteBadge(sizedLike: StoreBadgeStyle, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val artwork = painterResource(sizedLike.artwork).intrinsicSize
    BoxWithConstraints(
        modifier = modifier.aspectRatio(artwork.width / artwork.height, matchHeightConstraintsFirst = true),
    ) {
        val height = maxHeight
        val shape = RoundedCornerShape(height * 0.16f)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(Color.Black)
                .border(width = 1.dp, color = Color(0xFFA6A6A6), shape = shape)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(height * 0.14f, Alignment.CenterHorizontally),
        ) {
            Icon(
                modifier = Modifier.size(height * 0.5f),
                painter = painterResource(Res.drawable.ic_link),
                contentDescription = null,
                tint = Color.White,
            )
            Text(
                text = stringResource(Res.string.website),
                style = MaterialTheme.typography.titleLarge,
                fontSize = with(LocalDensity.current) { (height * 0.4f).toSp() },
                color = Color.White,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
        }
    }
}

@Composable
@Preview
private fun LinkBadgePreview() {
    Column(
        modifier = Modifier.padding(Dimens.small),
        verticalArrangement = Arrangement.spacedBy(Dimens.xSmall),
    ) {
        LinkBadge(
            type = LinkBadgeType.AppStore(AppForStoreLink.PICKY),
            modifier = Modifier.width(150.dp)
        )
        LinkBadge(
            type = LinkBadgeType.Website("https://picky.ink"),
            modifier = Modifier.width(150.dp)
        )
        LinkBadge(
            type = LinkBadgeType.Website("https://vipps.no", sizedLike = StoreBadgeStyle.PLAY_STORE),
            modifier = Modifier.width(150.dp)
        )
        LinkBadge(
            type = LinkBadgeType.PlayStore(AppForStoreLink.BANKID),
            modifier = Modifier.width(150.dp)
        )
    }
}

sealed class LinkBadgeType(val url: String) {
    data class PlayStore(val app: AppForStoreLink) :
        LinkBadgeType("https://play.google.com/store/apps/details?id=${app.urlIdentifier}")

    data class AppStore(val app: AppForStoreLink) :
        LinkBadgeType("https://apps.apple.com/app/${app.urlIdentifier}")

    /** @param sizedLike the store badge this button sits beside, so both are exactly the same size */
    data class Website(
        val linkUrl: String,
        val sizedLike: StoreBadgeStyle = StoreBadgeStyle.APP_STORE,
    ) : LinkBadgeType(linkUrl)
}

enum class StoreBadgeStyle(internal val artwork: DrawableResource) {
    APP_STORE(Res.drawable.app_store_badge),
    PLAY_STORE(Res.drawable.google_play_badge),
}

enum class AppForStoreLink(val urlIdentifier: String) {
    PICKY("picky-tattooing-companion/id6740286413"),
    BREEZ("make-life-a-breez/id6801916308"),
    BANKID("no.vipps.bankid"),
    VIPPS("no.dnb.vipps"),
    NRK_TV("no.nrk.tv"),
    HOLTE("com.holte.portal"),
}