package theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.app_store_badge
import jasontoms.composeapp.generated.resources.google_play_badge
import jasontoms.composeapp.generated.resources.ic_link
import jasontoms.composeapp.generated.resources.website
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.Dimens

@Composable
fun LinkBadge(type: LinkBadgeType, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    when (type) {
        is LinkBadgeType.AppStore -> {
            Image(
                modifier = modifier
                    .defaultMinSize(minWidth = 150.dp, minHeight = 48.dp)
                    .clickable { uriHandler.openUri(type.url) },
                painter = painterResource(Res.drawable.app_store_badge),
                contentDescription = "Download on the App Store",
                contentScale = ContentScale.FillWidth,
            )

        }

        is LinkBadgeType.PlayStore -> {
            Image(
                modifier = modifier
                    .defaultMinSize(minWidth = 150.dp, minHeight = 48.dp)
                    .clickable { uriHandler.openUri(type.url) },
                painter = painterResource(Res.drawable.google_play_badge),
                contentDescription = "Get it on Google Play",
                contentScale = ContentScale.FillWidth,
            )
        }

        is LinkBadgeType.Website -> {
            Row(
                modifier = modifier
                    .defaultMinSize(minWidth = 150.dp, minHeight = 48.dp)
                    .background(color = Color.Black, shape = MaterialTheme.shapes.small)
                    .border(width = 1.dp, color = Color(0xFFA6A6A6), shape = MaterialTheme.shapes.small)
                    .clickable { uriHandler.openUri(type.url) }
                    .clip(MaterialTheme.shapes.small)
                    .padding(Dimens.xSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    Dimens.xSmall,
                    Alignment.CenterHorizontally
                )
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.ic_link),
                    contentDescription = null,
                    tint = Color.White,
                )
                Text(
                    text = stringResource(Res.string.website),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            }
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

    data class Website(val linkUrl: String) : LinkBadgeType(linkUrl)
}

enum class AppForStoreLink(val urlIdentifier: String) {
    PICKY("picky-tattooing-companion/id6740286413"),
    BANKID("no.vipps.bankid"),
    VIPPS("no.dnb.vipps"),
    NRK_TV("no.nrk.tv"),
    HOLTE("com.holte.portal"),
}