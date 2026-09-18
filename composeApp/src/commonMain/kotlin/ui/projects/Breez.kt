package ui.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.breez_background
import jasontoms.composeapp.generated.resources.project_3_description
import jasontoms.composeapp.generated.resources.project_3_title
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.breezDarkTeal
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import ui.ContentCard
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Breez(modifier: Modifier = Modifier) {
    ContentCard(
        modifier = modifier,
        backgroundColor = Color.Unspecified,
        borderColor = breezDarkTeal,
        image = { BreezIcon() },
        details = { BreezDetails() },
        backgroundImage = Res.drawable.breez_background,
        placement = ContentCardPlacement.END,
    )
}

@Composable
private fun BreezIcon() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium),
            model = CdnImage.BREEZ_ICON.url,
            contentDescription = null,
        )
    }
}

@Composable
private fun BreezDetails() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        TextWithBackground(
            text = stringResource(Res.string.project_3_title),
            style = MaterialTheme.typography.displaySmall
        )
        TextWithBackground(
            text = stringResource(Res.string.project_3_description),
            style = MaterialTheme.typography.bodyMedium,
        )

        FlowRow(
            itemVerticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.small),
            verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)
        ) {
            LinkBadge(
                modifier = Modifier.height(50.dp),
                type = LinkBadgeType.AppStore(AppForStoreLink.BREEZ)
            )
            LinkBadge(
                modifier = Modifier.height(50.dp),
                type = LinkBadgeType.Website("https://breez.life")
            )
        }
    }
}
