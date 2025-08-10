package ui.work

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ui.Section
import ui.WebsiteSection

@Composable
fun WorkContent() {
    Section(section = WebsiteSection.WORK) {
        Text(
            text = "SECTION UNDER CONSTRUCTION",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}