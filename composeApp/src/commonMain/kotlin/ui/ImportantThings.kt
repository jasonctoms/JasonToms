package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.important_things
import jasontoms.composeapp.generated.resources.important_things_description
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.VerticalSpacer
import utils.ImageUrls

@Composable
fun PersonalContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionHeader(stringResource(Res.string.important_things))
        VerticalSpacer(Dimens.medium)
        Text(
            text = stringResource(Res.string.important_things_description),
            color = MaterialTheme.colorScheme.onBackground
        )
        VerticalSpacer(Dimens.small)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.xSmall),
            horizontalArrangement = Arrangement.spacedBy(Dimens.xSmall)
        ) {
            PersonalImage(ImageUrls.STELLA_JASON_ULRIKEN.url)
            PersonalImage(ImageUrls.STORM_IN_BOX.url)
            PersonalImage(ImageUrls.STORM_AND_LUPIN.url)
            PersonalImage(ImageUrls.GREY_THING_SOON.url)
            PersonalImage(ImageUrls.BROOMY_IN_CHAIR.url)
        }
    }
}

@Composable
private fun PersonalImage(url: String) {
    AsyncImage(
        modifier = Modifier
            .size(200.dp)
            .clip(MaterialTheme.shapes.medium),
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}