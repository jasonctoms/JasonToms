package ui.projects

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ui.Section
import ui.WebsiteSection

@Composable
fun ProjectsContent() {
    Section(section = WebsiteSection.PROJECTS) {
        Text(
            text = "SECTION UNDER CONSTRUCTION",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}