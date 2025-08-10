package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.personal_title
import jasontoms.composeapp.generated.resources.projects_title
import jasontoms.composeapp.generated.resources.school_title
import jasontoms.composeapp.generated.resources.work_title
import org.jetbrains.compose.resources.StringResource
import theme.AppTheme
import theme.Dimens
import theme.Previews
import theme.components.WebsiteBackground
import ui.footer.Footer
import ui.header.ScreenHeader
import ui.personal.PersonalContent
import ui.projects.ProjectsContent
import ui.school.SchoolContent
import ui.work.WorkContent

@Composable
fun WebsiteContent() {
    WebsiteBackground()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.large),
    ) {
        ScreenHeader()
        WebsiteSection.entries.forEach { section ->
            when (section) {
                WebsiteSection.PERSONAL -> PersonalContent()
                WebsiteSection.PROJECTS -> ProjectsContent()
                WebsiteSection.WORK -> WorkContent()
                WebsiteSection.SCHOOL -> SchoolContent()
            }
        }
        Footer()
    }
}

enum class WebsiteSection(val titleRes: StringResource) {
    PERSONAL(Res.string.personal_title),
    PROJECTS(Res.string.projects_title),
    WORK(Res.string.work_title),
    SCHOOL(Res.string.school_title),
}

@Previews
@Composable
fun StartScreenPreview() {
    AppTheme {
        WebsiteContent()
    }
}
