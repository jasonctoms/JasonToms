package ui.personal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_soapbox
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.SelectableText
import utils.CdnImage

@Composable
fun Soapbox(modifier: Modifier = Modifier) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    val shape = MaterialTheme.shapes.extraLarge
    val cardModifier = modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.92f), shape = shape)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = shape)
        .padding(if (widthClass == WindowWidthSizeClass.Compact) Dimens.medium else Dimens.large)

    if (widthClass == WindowWidthSizeClass.Expanded) {
        Row(
            modifier = cardModifier,
            horizontalArrangement = Arrangement.spacedBy(Dimens.large),
            verticalAlignment = Alignment.Top,
        ) {
            SoapboxText(modifier = Modifier.weight(3f))
            Column(
                modifier = Modifier.weight(1.2f),
                verticalArrangement = Arrangement.spacedBy(Dimens.small),
            ) {
                SoapboxImage(image = CdnImage.UNITY_SIGN)
                SoapboxImage(image = CdnImage.HUMAN_CENSUS)
            }
        }
    } else {
        Column(
            modifier = cardModifier,
            verticalArrangement = Arrangement.spacedBy(Dimens.medium),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.small)
            ) {
                SoapboxImage(image = CdnImage.UNITY_SIGN, modifier = Modifier.weight(1f))
                SoapboxImage(image = CdnImage.HUMAN_CENSUS, modifier = Modifier.weight(1f))
            }
            SoapboxText()
        }
    }
}

@Composable
private fun SoapboxText(modifier: Modifier = Modifier) {
    SelectableText(
        modifier = modifier,
        text = stringResource(Res.string.personal_soapbox),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun SoapboxImage(image: CdnImage, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large),
        model = image.url,
        contentDescription = null,
    )
}

@Previews
@Composable
private fun SoapboxPreview() {
    ContentPreview {
        Soapbox()
    }
}
