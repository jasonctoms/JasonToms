package ui.personal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.VerticalSpacer
import ui.Section
import ui.WebsiteSection
import ui.containerCard

@Composable
fun PersonalContent(modifier: Modifier = Modifier) {
    LocalWindowSizeClass.current?.widthSizeClass?.let { widthClass ->
        Section(section = WebsiteSection.PERSONAL, modifier = modifier) {
            Intro()
            VerticalSpacer(Dimens.medium)
            Soapbox(
                modifier = Modifier
                    .fillMaxWidth()
                    .containerCard(
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        borderColor = MaterialTheme.colorScheme.secondary
                    ).padding(Dimens.large),
                widthClass = widthClass,
            )
        }
    }
}

@Composable
@Previews
private fun PersonalPreview() {
    ContentPreview {
        PersonalContent()
    }
}