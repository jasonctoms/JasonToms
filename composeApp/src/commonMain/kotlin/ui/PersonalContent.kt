package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.important_things
import jasontoms.composeapp.generated.resources.important_things_description
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.AdaptiveFlowRow
import theme.components.ItemsPerRow
import theme.components.VerticalSpacer
import utils.ImageUrls

@Composable
fun PersonalContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionHeader(stringResource(Res.string.important_things))
        VerticalSpacer(Dimens.small)
        Text(
            text = stringResource(Res.string.important_things_description),
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
            PersonalImage(ImageUrls.STELLA_JASON_ULRIKEN.url, itemWidth)
            PersonalImage(ImageUrls.STORM_IN_BOX.url, itemWidth)
            PersonalImage(ImageUrls.STORM_AND_LUPIN.url, itemWidth)
            PersonalImage(ImageUrls.GREY_THING_SOON.url, itemWidth)
            PersonalImage(ImageUrls.BROOMY_IN_CHAIR.url, itemWidth)
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