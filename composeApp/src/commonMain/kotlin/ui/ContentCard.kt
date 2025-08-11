package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.Dimens

@Composable
fun ContentCard(modifier: Modifier = Modifier) {

}

@Composable
fun Modifier.containerCard(
    backgroundColor: Color,
    borderColor: Color
): Modifier = this
    .background(
        color = backgroundColor.copy(alpha = 0.9f),
        shape = MaterialTheme.shapes.extraLarge,
    )
    .border(
        width = 10.dp,
        color = borderColor,
        shape = MaterialTheme.shapes.extraLarge
    )
    .padding(Dimens.large)
