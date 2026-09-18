package ui.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.portfolio_subtitle
import jasontoms.composeapp.generated.resources.portfolio_title
import kotlinx.coroutines.launch
import navigation.Route
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.ContentColumn
import theme.components.SelectableText
import theme.sectionSpacing
import ui.WebsiteSection
import ui.footer.Footer
import ui.projects.ProjectsContent
import ui.school.SchoolContent
import ui.work.WorkContent

private val portfolioSections = listOf(WebsiteSection.PROJECTS, WebsiteSection.WORK, WebsiteSection.SCHOOL)

@Composable
fun PortfolioScreen(onNavigate: (Route) -> Unit, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    // leave a little breathing room above a section when jumping to it
    val jumpOffset = with(LocalDensity.current) { -Dimens.medium.roundToPx() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(sectionSpacing()),
        contentPadding = PaddingValues(top = Dimens.medium),
    ) {
        item {
            PortfolioHeader(
                onSectionClick = { section ->
                    // the header is item 0, so each section's index is shifted by one
                    val index = portfolioSections.indexOf(section) + 1
                    scope.launch { listState.animateScrollToItem(index, jumpOffset) }
                }
            )
        }
        portfolioSections.forEach { section ->
            item(key = section) {
                when (section) {
                    WebsiteSection.PROJECTS -> ProjectsContent()
                    WebsiteSection.WORK -> WorkContent()
                    WebsiteSection.SCHOOL -> SchoolContent()
                    WebsiteSection.PERSONAL -> Unit
                }
            }
        }
        item { Footer(onNavigate = onNavigate) }
    }
}

@Composable
private fun PortfolioHeader(onSectionClick: (WebsiteSection) -> Unit) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    ContentColumn(verticalArrangement = Arrangement.spacedBy(Dimens.small)) {
        SelectableText(
            text = stringResource(Res.string.portfolio_title),
            style = if (compact) MaterialTheme.typography.displayMedium else MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        SelectableText(
            modifier = Modifier.widthIn(max = Dimens.maxReadingWidth),
            text = stringResource(Res.string.portfolio_subtitle),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = null),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FlowRow(
            modifier = Modifier.padding(top = Dimens.xSmall),
            horizontalArrangement = Arrangement.spacedBy(Dimens.xSmall),
        ) {
            portfolioSections.forEach { section ->
                SuggestionChip(
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                    onClick = { onSectionClick(section) },
                    label = {
                        Text(
                            modifier = Modifier.padding(vertical = Dimens.xSmall),
                            text = stringResource(section.shortTitleRes),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    },
                    shape = MaterialTheme.shapes.extraLarge,
                )
            }
        }
    }
}

@Previews
@Composable
private fun PortfolioScreenPreview() {
    AppTheme {
        PortfolioScreen(onNavigate = {})
    }
}
