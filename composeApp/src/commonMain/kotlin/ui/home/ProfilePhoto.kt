package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.derpdroid
import org.jetbrains.compose.resources.painterResource
import theme.Dimens
import theme.components.DoubleSidedLayout
import theme.components.FlipDirection
import utils.CdnImage

/**
 * Round profile photo with a ring in the page background color, so it can overlap the hero banner.
 * Clicking it flips it over to reveal Derpdroid.
 */
@Composable
fun ProfilePhoto(size: Dp, modifier: Modifier = Modifier) {
    var faceUp by remember { mutableStateOf(true) }
    val ring = Modifier
        .size(size)
        .border(width = 5.dp, color = MaterialTheme.colorScheme.background, shape = CircleShape)
        .padding(5.dp)
        .clip(CircleShape)

    DoubleSidedLayout(
        modifier = modifier
            .clip(CircleShape)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { faceUp = !faceUp },
        faceUp = faceUp,
        flipDirection = FlipDirection.HORIZONTAL,
        front = {
            AsyncImage(
                modifier = ring,
                model = CdnImage.PROFILE.url,
                contentDescription = "Jason Toms",
                contentScale = ContentScale.Crop,
            )
        },
        back = {
            Image(
                // the layout is rotated to show this side, so un-rotate it to keep it from being mirrored
                modifier = ring
                    .graphicsLayer { rotationY = -180f }
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(Dimens.medium)
                    .fillMaxSize(),
                painter = painterResource(Res.drawable.derpdroid),
                contentDescription = "Derpdroid",
            )
        },
    )
}
