package ui.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.project_2_description
import jasontoms.composeapp.generated.resources.project_2_title
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.SelectableText
import theme.dockerBlue
import theme.linuxMintGreen
import ui.ContentCard
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.HomeServer(modifier: Modifier = Modifier) {
    ContentCard(
        modifier = modifier,
        backgroundColor = dockerBlue,
        borderColor = linuxMintGreen,
        image = { HomeServerImages() },
        details = { HomeServerDescription() },
        placement = ContentCardPlacement.END,
    )
}

@Composable
private fun HomeServerImages() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .padding(Dimens.medium)
                .clip(MaterialTheme.shapes.medium),
            model = CdnImage.LINUX_MINT_ICON.url,
            contentDescription = null,
        )
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .padding(Dimens.medium)
                .clip(MaterialTheme.shapes.medium),
            model = CdnImage.DOCKER_ICON.url,
            contentDescription = null,
        )
    }
}

@Composable
private fun HomeServerDescription() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        SelectableText(
            text = stringResource(Res.string.project_2_title),
            style = MaterialTheme.typography.displaySmall,
            color = Color.White,
        )
        SelectableText(
            text = stringResource(Res.string.project_2_description),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )
    }
}