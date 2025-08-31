package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.derpdroid
import jasontoms.composeapp.generated.resources.picky_background
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.lsuGold
import theme.lsuPurple
import ui.projects.TextWithBackground

@Composable
fun ColumnScope.ContentCard(
    backgroundColor: Color,
    borderColor: Color,
    image: @Composable () -> Unit,
    details: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    placement: ContentCardPlacement = ContentCardPlacement.START,
    backgroundImage: DrawableResource? = null,
) {
    ContentCard(
        backgroundColor = backgroundColor,
        borderBrush = SolidColor(borderColor),
        image = image,
        details = details,
        modifier = modifier,
        placement = placement,
        backgroundImage = backgroundImage,
    )
}

@Composable
fun ColumnScope.ContentCard(
    backgroundColor: Color,
    borderBrush: Brush,
    image: @Composable () -> Unit,
    details: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    placement: ContentCardPlacement = ContentCardPlacement.START,
    backgroundImage: DrawableResource? = null,
) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    val cardWidth = when {
        placement == ContentCardPlacement.FULL_WIDTH -> 1f
        widthClass == WindowWidthSizeClass.Compact -> 1f
        widthClass == WindowWidthSizeClass.Medium -> 0.85f
        widthClass == WindowWidthSizeClass.Expanded -> 0.8f
        else -> 1f
    }
    Box(
        modifier = modifier
            .align(
                if (placement == ContentCardPlacement.END) {
                    Alignment.End
                } else {
                    Alignment.Start
                }
            )
            .fillMaxWidth(cardWidth)
            .containerCard(backgroundColor, borderBrush)
    ) {
        backgroundImage?.let {
            Image(
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.shapes.extraLarge),
                painter = painterResource(it),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        if (widthClass == WindowWidthSizeClass.Expanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.large),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.small)
            ) {
                if (placement == ContentCardPlacement.START) {
                    Box(modifier = Modifier.weight(2f)) { image() }
                    Box(modifier = Modifier.weight(3f)) { details() }
                } else {
                    Box(modifier = Modifier.weight(3f)) { details() }
                    Box(modifier = Modifier.weight(2f)) { image() }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth().padding(Dimens.large),
                verticalArrangement = Arrangement.spacedBy(Dimens.small),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (placement == ContentCardPlacement.FULL_WIDTH) {
                    details()
                    image()
                } else {
                    image()
                    details()
                }
            }
        }
    }
}

@Composable
fun Modifier.containerCard(
    backgroundColor: Color,
    borderColor: Color
): Modifier = this.containerCard(backgroundColor, SolidColor(borderColor))

@Composable
fun Modifier.containerCard(
    backgroundColor: Color,
    borderBrush: Brush
): Modifier = this
    .background(
        color = backgroundColor.copy(alpha = 0.9f),
        shape = MaterialTheme.shapes.extraLarge,
    )
    .border(
        width = 10.dp,
        brush = borderBrush,
        shape = MaterialTheme.shapes.extraLarge
    )

enum class ContentCardPlacement { START, END, FULL_WIDTH }

@Composable
@Previews
private fun ContentCardPreview() {
    ContentPreview {
        ContentCard(
            backgroundColor = Color.Unspecified,
            borderColor = Color.Black,
            image = { ContentCardImage() },
            details = { ContentCardDetails(true) },
            backgroundImage = Res.drawable.picky_background,
        )
        ContentCard(
            backgroundColor = lsuPurple,
            borderColor = lsuGold,
            placement = ContentCardPlacement.END,
            image = { ContentCardImage() },
            details = { ContentCardDetails() }
        )
        ContentCard(
            backgroundColor = lsuPurple,
            borderColor = lsuGold,
            placement = ContentCardPlacement.FULL_WIDTH,
            image = { ContentCardImage() },
            details = { ContentCardDetails() }
        )
    }
}

@Composable
private fun ContentCardDetails(backgroundImage: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        if (backgroundImage) {
            TextWithBackground(
                text = "Content",
                style = MaterialTheme.typography.headlineMedium
            )
            TextWithBackground(
                text = "Content",
                style = MaterialTheme.typography.labelLarge
            )
            TextWithBackground(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
            TextWithBackground(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
            TextWithBackground(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = "Content",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Content",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ContentCardImage() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium),
            painter = painterResource(Res.drawable.derpdroid),
            contentDescription = null,
        )
    }
}
