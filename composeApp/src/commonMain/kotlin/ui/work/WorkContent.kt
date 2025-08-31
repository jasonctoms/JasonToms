package ui.work

import androidx.compose.runtime.Composable
import theme.ContentPreview
import theme.Previews
import ui.ContentCardPlacement
import ui.Section
import ui.WebsiteSection

@Composable
fun WorkContent() {
    Section(section = WebsiteSection.WORK) {
        Stuh(placement = ContentCardPlacement.START)
        Vipps(placement = ContentCardPlacement.END)
        Nrk(placement = ContentCardPlacement.START)
        Holte(placement = ContentCardPlacement.END)
        Kongsberg(placement = ContentCardPlacement.START)
    }
}

@Composable
@Previews
private fun WorkPreview() {
    ContentPreview {
        WorkContent()
    }
}