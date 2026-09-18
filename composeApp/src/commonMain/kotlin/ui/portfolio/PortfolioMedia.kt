package ui.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import theme.Dimens
import theme.LocalWindowSizeClass
import utils.CdnImage

/** Rounded like an app icon, so logos and app icons read as the same kind of thing. */
private val tileShape = RoundedCornerShape(percent = 22)

/** Company logos are mostly wide wordmarks, so their tiles are wider than they are tall. */
const val WORDMARK_ASPECT_RATIO = 1.5f

/**
 * A logo and the tile it sits on.
 *
 * @param background the color the logo was designed to sit on, so logos made for dark or colored
 * backgrounds stay legible now that the cards themselves are neutral
 * @param fillsTile for logo images that already include their own background edge to edge: the
 * image fills the whole tile instead of sitting inside it with padding
 */
data class Logo(
    val image: CdnImage,
    val background: Color,
    val fillsTile: Boolean = false,
)

/** A logo on its tile. [size] is the tile's height; its width is `size * aspectRatio`. */
@Composable
fun LogoTile(logo: Logo, size: Dp, modifier: Modifier = Modifier, aspectRatio: Float = 1f) {
    Box(
        modifier = modifier
            .size(width = size * aspectRatio, height = size)
            .clip(tileShape)
            .background(logo.background)
            .padding(if (logo.fillsTile) 0.dp else size * 0.14f),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = logo.image.url,
            contentDescription = null,
            contentScale = if (logo.fillsTile) ContentScale.Crop else ContentScale.Fit,
        )
    }
}

/** Logos side by side, e.g. a company and its best-known product. */
@Composable
fun LogoTiles(vararg logos: Logo, size: Dp, aspectRatio: Float = 1f) {
    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        logos.forEach { LogoTile(logo = it, size = size, aspectRatio = aspectRatio) }
    }
}

/** An app's own icon, which already has its own background. */
@Composable
fun AppIcon(icon: CdnImage, size: Dp, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier
            .size(size)
            .clip(tileShape),
        model = icon.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

/**
 * Square photo thumbnails for the body of a card. Every thumbnail is the same size no matter how
 * many photos there are (four across, or two on phones), so a single photo stays a thumbnail
 * instead of stretching across the whole card.
 */
@Composable
fun CardPhotoStrip(photos: List<CdnImage>, modifier: Modifier = Modifier) {
    val perRow = if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) 2 else 4
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        photos.chunked(perRow).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
                row.forEach {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium),
                        model = it.url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
                repeat(perRow - row.size) { Spacer(Modifier.weight(1f)) }
            }
        }
    }
}
