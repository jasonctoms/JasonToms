package theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import theme.components.ContentColumn

private object PreviewDimens {
    // Phone-like sizes
    const val PHONE_WIDTH = 360
    const val PHONE_HEIGHT = 740

    // Tablet-like sizes
    const val TABLET_WIDTH = 768
    const val TABLET_HEIGHT = 1024

    // Desktop-like sizes
    const val DESKTOP_WIDE_WIDTH = 1920
    const val DESKTOP_WIDE_HEIGHT = 1080
}


@Preview(
    name = "Compact",
    widthDp = PreviewDimens.PHONE_WIDTH,
    heightDp = PreviewDimens.PHONE_HEIGHT
)
@Preview(
    name = "Medium",
    widthDp = PreviewDimens.TABLET_WIDTH,
    heightDp = PreviewDimens.TABLET_HEIGHT
)
@Preview(
    name = "Expanded",
    widthDp = PreviewDimens.DESKTOP_WIDE_WIDTH,
    heightDp = PreviewDimens.DESKTOP_WIDE_HEIGHT
)
annotation class Previews

@Composable
fun ContentPreview(content: @Composable ColumnScope.() -> Unit) {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ContentColumn(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}