package ui.projects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.ContentPreview
import theme.Previews
import ui.Section
import ui.WebsiteSection

@Composable
fun ProjectsContent() {
    Section(section = WebsiteSection.PROJECTS) {
        Picky()
        HomeServer()
    }
}

@Composable
@Previews
private fun ProjectsPreview() {
    ContentPreview {
        ProjectsContent()
    }
}