package theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.Dimens

@Composable
fun ContentColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Dimens.medium),
    content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .sizeIn(maxWidth = 1200.dp)
            .padding(horizontal = Dimens.small)
            .then(modifier),
        verticalArrangement = verticalArrangement,
    ) {
        content()
    }
}