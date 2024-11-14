package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.important_things_description
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import utils.ImageUrls
import utils.VerticalSpacer

@Composable
fun ImportantThings(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.important_things_description),
            color = MaterialTheme.colorScheme.onBackground
        )
        VerticalSpacer(Dimens.small)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.xSmall)
        ) {
            ImportantImage(ImageUrls.STELLA_JASON_ULRIKEN.url)
            ImportantImage(ImageUrls.STORM_IN_BOX.url)
            ImportantImage(ImageUrls.STORM_AND_LUPIN.url)
            ImportantImage(ImageUrls.GREY_THING_SOON.url)
            ImportantImage(ImageUrls.BROOMY_IN_CHAIR.url)
        }
    }
}

@Composable
private fun ImportantImage(url: String) {
    AsyncImage(
        modifier = Modifier.width(200.dp),
        model = url,
        contentDescription = null,
    )
}