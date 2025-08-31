package ui.personal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_soapbox
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.components.SelectableText
import utils.CdnImage

@Composable
fun Soapbox(modifier: Modifier = Modifier) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    if (widthClass == WindowWidthSizeClass.Compact) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.small)
        ) {
            SelectableText(
                text = stringResource(Res.string.personal_soapbox),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.small)
            ) {
                SoapboxImage(image = CdnImage.UNITY_SIGN, modifier = Modifier.weight(1f))
                SoapboxImage(image = CdnImage.HUMAN_CENSUS, modifier = Modifier.weight(1f))
            }
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Dimens.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SoapboxImage(image = CdnImage.UNITY_SIGN, modifier = Modifier.weight(1f))
            SelectableText(
                modifier = Modifier.weight(3f),
                text = stringResource(Res.string.personal_soapbox),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall
            )
            SoapboxImage(image = CdnImage.HUMAN_CENSUS, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun SoapboxImage(image: CdnImage, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        model = image.url,
        contentDescription = null,
    )
}
