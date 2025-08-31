package ui.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_message
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.AdaptiveFlowRow
import theme.components.ItemsPerRow
import theme.components.SelectableText
import theme.components.VerticalSpacer
import utils.CdnImage

@Composable
fun Intro() {
    Column {
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
    }
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
