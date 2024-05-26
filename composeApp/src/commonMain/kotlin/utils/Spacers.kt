package utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun ColumnScope.ExpandingSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun RowScope.ExpandingSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}