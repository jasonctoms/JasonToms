package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import theme.AppTheme
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.ContentColumn
import theme.components.HorizontalSpacer
import utils.ImageUrls

@Composable
fun ScreenHeader(modifier: Modifier = Modifier) {

    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize().sizeIn(maxHeight = 500.dp),
            model = ImageUrls.SOGNEFJORD.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
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
        ContentColumn(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = Dimens.medium, horizontal = Dimens.small)
        ) {
            if (widthClass == WindowWidthSizeClass.Compact) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.small),
                ) {
                    ProfilePhoto()
                    HorizontalSpacer(Dimens.medium)
                    BiographyText()
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    ProfilePhoto()
                    HorizontalSpacer(Dimens.medium)
                    BiographyText()
                }
            }
        }
    }
}

@Previews
@Composable
fun ScreenHeaderPreview() {
    AppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenHeader()
        }
    }
}