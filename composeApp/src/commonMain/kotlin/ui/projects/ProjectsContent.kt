package ui.projects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import theme.components.VerticalSpacer
import ui.Section
import ui.WebsiteSection

@Composable
fun ProjectsContent() {
    Section(section = WebsiteSection.PROJECTS) {
        Picky()
        VerticalSpacer(Dimens.medium)
        HomeServer(modifier = Modifier.align(Alignment.End))
    }
}

@Composable
@Previews
private fun ProjectsPreview() {
    ContentPreview {
        ProjectsContent()
    }
}