package theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.Dimens
import theme.LocalWindowSizeClass

@Composable
fun AdaptiveFlowRow(
    itemsPerRow: ItemsPerRow,
    modifier: Modifier = Modifier,
    content: @Composable (itemWidth: Dp) -> Unit
) {
    LocalWindowSizeClass.current.widthSizeClass.let { widthClass ->
        val itemsPerRow = when (widthClass) {
            WindowWidthSizeClass.Compact -> itemsPerRow.compact
            WindowWidthSizeClass.Medium -> itemsPerRow.medium
            WindowWidthSizeClass.Expanded -> itemsPerRow.expanded
            else -> itemsPerRow.expanded
        }

        BoxWithConstraints(modifier = modifier) {
            val itemWidth = (maxWidth) / itemsPerRow - 1.dp

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.xSmall),
                itemVerticalAlignment = Alignment.CenterVertically,
            ) {
                content(itemWidth)
            }
        }
    }
}

data class ItemsPerRow(
    val compact: Int,
    val medium: Int,
    val expanded: Int
)