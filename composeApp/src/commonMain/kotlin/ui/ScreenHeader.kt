package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import theme.Dimens
import utils.HorizontalSpacer
import utils.ImageUrls

@Composable
fun ScreenHeader() {
    val height = 500.dp
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(height),
            model = ImageUrls.SOGNEFJORD.url,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                        ),
                        startY = 300f
                    )
                )
        )
        Row(
            modifier = Modifier.fillMaxWidth().height(height).padding(
                top = Dimens.extraLarge,
                bottom = Dimens.medium,
                start = Dimens.extraLarge,
                end = Dimens.extraLarge,
            ),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
        ) {
            ProfilePhoto()
            HorizontalSpacer(Dimens.medium)
            BiographyText()
        }
    }
}