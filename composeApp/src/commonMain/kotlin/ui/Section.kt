package ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.ContentColumn
import theme.components.SectionHeader
import theme.components.VerticalSpacer

@Composable
fun Section(
    section: WebsiteSection,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    ContentColumn(modifier = modifier.fillMaxSize()) {
        SectionHeader(header = stringResource(section.titleRes))
        VerticalSpacer(Dimens.small)
        content()
    }
}