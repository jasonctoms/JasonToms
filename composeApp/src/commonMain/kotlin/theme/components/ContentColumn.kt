package theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import theme.Dimens
import theme.pageGutter

/**
 * A full-width column capped at [maxWidth], with the adaptive page gutter on either side.
 * Callers are expected to center it horizontally.
 */
@Composable
fun ContentColumn(
    modifier: Modifier = Modifier,
    maxWidth: Dp = Dimens.maxContentWidth,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Dimens.medium),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .widthIn(max = maxWidth + pageGutter() * 2)
            .fillMaxWidth()
            .padding(horizontal = pageGutter())
            .then(modifier),
        verticalArrangement = verticalArrangement,
    ) {
        content()
    }
}
