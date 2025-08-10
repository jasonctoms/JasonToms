package ui.school

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ui.Section
import ui.WebsiteSection

@Composable
fun SchoolContent() {
    Section(section = WebsiteSection.SCHOOL) {
        Text(
            text = "SECTION UNDER CONSTRUCTION",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}