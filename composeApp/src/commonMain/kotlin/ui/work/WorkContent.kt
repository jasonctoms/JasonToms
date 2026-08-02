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
        Insulet(placement = ContentCardPlacement.START)
        Stuh(placement = ContentCardPlacement.END)
        Vipps(placement = ContentCardPlacement.START)
        Nrk(placement = ContentCardPlacement.END)
        Holte(placement = ContentCardPlacement.START)
        Kongsberg(placement = ContentCardPlacement.END)
    }
}

@Composable
@Previews
private fun WorkPreview() {
    ContentPreview {
        WorkContent()
    }
}