package ui.projects

import androidx.compose.runtime.Composable
import theme.ContentPreview
import theme.Previews
import ui.Section
import ui.WebsiteSection

@Composable
fun ProjectsContent() {
    Section(section = WebsiteSection.PROJECTS) {
        Picky()
        Breez()
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
