package ui.work

import androidx.compose.runtime.Composable
import theme.ContentPreview
import theme.Previews
import ui.Section
import ui.WebsiteSection

@Composable
fun WorkContent() {
    Section(section = WebsiteSection.WORK) {
        Insulet()
        Stuh()
        Vipps()
        Nrk()
        Holte()
        Kongsberg()
    }
}

@Composable
@Previews
private fun WorkPreview() {
    ContentPreview {
        WorkContent()
    }
}
