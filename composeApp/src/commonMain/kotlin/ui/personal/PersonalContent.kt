package ui.personal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import ui.Section
import ui.WebsiteSection
import ui.containerCard

@Composable
fun PersonalContent(modifier: Modifier = Modifier) {
    Section(section = WebsiteSection.PERSONAL, modifier = modifier) {
        Intro()
        Soapbox(
            modifier = Modifier
                .fillMaxWidth()
                .containerCard(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    borderColor = MaterialTheme.colorScheme.secondary
                ).padding(Dimens.large),
        )
    }
}

@Composable
@Previews
private fun PersonalPreview() {
    ContentPreview {
        PersonalContent()
    }
}