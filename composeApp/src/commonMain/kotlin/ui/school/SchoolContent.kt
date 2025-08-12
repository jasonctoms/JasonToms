package ui.school

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.school_degree
import jasontoms.composeapp.generated.resources.school_description
import jasontoms.composeapp.generated.resources.school_location
import jasontoms.composeapp.generated.resources.school_name
import jasontoms.composeapp.generated.resources.school_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import theme.components.AdaptiveFlowRow
import theme.components.ItemsPerRow
import theme.lsuGold
import theme.lsuPurple
import ui.ContentCard
import ui.Section
import ui.WebsiteSection
import utils.ImageUrls

@Composable
fun SchoolContent() {
    Section(section = WebsiteSection.SCHOOL) {
        ContentCard(
            backgroundColor = lsuPurple,
            borderColor = lsuGold,
            fullWidth = true,
            image = { SchoolPictures() },
            details = { SchoolDescription() }
        )
    }
}

@Composable
private fun SchoolDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)
    ) {
        Text(
            text = stringResource(Res.string.school_name),
            color = Color.White,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(Res.string.school_location),
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontStyle = FontStyle.Italic,
        )
        Text(
            text = stringResource(Res.string.school_degree),
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.school_years),
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(Res.string.school_description),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SchoolPictures(modifier: Modifier = Modifier) {
    AdaptiveFlowRow(
        modifier = modifier,
        itemsPerRow = ItemsPerRow(2, 4, 2)
    ) { itemWidth ->
        Pictures(
            itemWidth = itemWidth,
            pictures = listOf(
                ImageUrls.ME_AT_LSU,
                ImageUrls.BREADBOARD,
                ImageUrls.OSCILLISCOPE,
                ImageUrls.MICROMIXER
            )
        )
    }
}

@Composable
private fun Pictures(itemWidth: Dp, pictures: List<ImageUrls>) {
    pictures.forEach {
        AsyncImage(
            modifier = Modifier
                .size(itemWidth)
                .padding(Dimens.xSmall)
                .clip(MaterialTheme.shapes.medium),
            model = it.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
@Previews
private fun SchoolContentPreview() {
    ContentPreview {
        SchoolContent()
    }
}