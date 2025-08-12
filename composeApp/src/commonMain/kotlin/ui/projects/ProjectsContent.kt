package ui.projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.picky_background
import jasontoms.composeapp.generated.resources.picky_icon
import jasontoms.composeapp.generated.resources.project_1_description
import jasontoms.composeapp.generated.resources.project_1_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import ui.ContentCard
import ui.Section
import ui.WebsiteSection

@Composable
fun ProjectsContent() {
    Section(section = WebsiteSection.PROJECTS) {
        ContentCard(
            backgroundColor = Color.Unspecified,
            borderColor = Color.Black,
            image = { PickyIcon() },
            details = { PickyDetails() },
            backgroundImage = Res.drawable.picky_background,
        )
    }
}

@Composable
private fun PickyDetails() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        TextWithBackground(
            text = stringResource(Res.string.project_1_title),
            style = MaterialTheme.typography.headlineMedium
        )
        TextWithBackground(
            text = stringResource(Res.string.project_1_description),
            style = MaterialTheme.typography.bodyLarge,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.small)
        ) {
            LinkBadge(
                modifier = Modifier.height(50.dp),
                type = LinkBadgeType.Website("https://picky.ink")
            )
            LinkBadge(
                modifier = Modifier.height(50.dp),
                type = LinkBadgeType.AppStore(AppForStoreLink.PICKY)
            )
        }
    }

}

@Composable
private fun PickyIcon() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium),
            painter = painterResource(Res.drawable.picky_icon),
            contentDescription = null,
        )
    }
}

@Composable
@Previews
private fun ProjectsPreview() {
    ContentPreview {
        ProjectsContent()
    }
}