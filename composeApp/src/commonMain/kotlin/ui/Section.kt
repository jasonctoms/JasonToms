package ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_title
import jasontoms.composeapp.generated.resources.projects_short
import jasontoms.composeapp.generated.resources.projects_title
import jasontoms.composeapp.generated.resources.school_short
import jasontoms.composeapp.generated.resources.school_title
import jasontoms.composeapp.generated.resources.work_short
import jasontoms.composeapp.generated.resources.work_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import theme.components.ContentColumn
import theme.components.SectionHeader

@Composable
fun Section(
    section: WebsiteSection,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Section(title = stringResource(section.titleRes), modifier = modifier, content = content)
}

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    ContentColumn(modifier = modifier) {
        SectionHeader(header = title)
        content()
    }
}

/**
 * @param shortTitleRes a one-word label for the section, used for in-page jump links.
 */
enum class WebsiteSection(val titleRes: StringResource, val shortTitleRes: StringResource) {
    PERSONAL(Res.string.personal_title, Res.string.personal_title),
    PROJECTS(Res.string.projects_title, Res.string.projects_short),
    WORK(Res.string.work_title, Res.string.work_short),
    SCHOOL(Res.string.school_title, Res.string.school_short),
}
