package ui.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import theme.Dimens
import theme.components.SelectableText

@Composable
fun TextWithBackground(
    text: String, modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    SelectableText(
        modifier = modifier
            .background(
                color = Color.Black.copy(alpha = 0.6f),
                shape = MaterialTheme.shapes.small
            ).padding(Dimens.small),
        text = text,
        style = style,
        color = Color.White,
    )
}