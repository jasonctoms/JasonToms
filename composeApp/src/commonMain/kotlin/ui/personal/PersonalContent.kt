package ui.personal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_message
import jasontoms.composeapp.generated.resources.personal_soapbox
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.AdaptiveFlowRow
import theme.components.ItemsPerRow
import theme.components.SelectableText
import theme.components.VerticalSpacer
import ui.Section
import ui.WebsiteSection
import ui.containerCard
import utils.CdnImage

@Composable
fun PersonalContent(modifier: Modifier = Modifier) {
    LocalWindowSizeClass.current?.widthSizeClass?.let { widthClass ->
        Section(section = WebsiteSection.PERSONAL, modifier = modifier) {
            SelectableText(
                text = stringResource(Res.string.personal_message),
                color = MaterialTheme.colorScheme.onBackground
            )
            VerticalSpacer(Dimens.small)
            AdaptiveFlowRow(
                modifier = Modifier.fillMaxWidth(),
                itemsPerRow = ItemsPerRow(
                    compact = 2,
                    medium = 4,
                    expanded = 5,
                )
            ) { itemWidth ->
                PersonalImage(CdnImage.STELLA_JASON_ULRIKEN.url, itemWidth)
                PersonalImage(CdnImage.STORM_IN_BOX.url, itemWidth)
                PersonalImage(CdnImage.STORM_AND_LUPIN.url, itemWidth)
                PersonalImage(CdnImage.GREY_THING_SOON.url, itemWidth)
                PersonalImage(CdnImage.BROOMY_IN_CHAIR.url, itemWidth)
            }
            VerticalSpacer(Dimens.medium)
            val containerModifier = Modifier
                .fillMaxWidth()
                .containerCard(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    borderColor = MaterialTheme.colorScheme.secondary
                ).padding(Dimens.large)
            if (widthClass == WindowWidthSizeClass.Compact) {
                Column(
                    modifier = containerModifier,
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
                    modifier = containerModifier,
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
    }
}

@Composable
private fun SoapboxImage(image: CdnImage, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier.fillMaxWidth().clip(MaterialTheme.shapes.medium),
        model = image.url,
        contentDescription = null,
    )
}

@Composable
private fun PersonalImage(url: String, size: Dp) {
    AsyncImage(
        modifier = Modifier
            .size(size)
            .padding(Dimens.xSmall)
            .clip(MaterialTheme.shapes.medium),
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
@Previews
private fun PersonalPreview() {
    ContentPreview {
        PersonalContent()
    }
}