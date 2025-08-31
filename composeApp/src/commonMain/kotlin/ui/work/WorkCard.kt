package ui.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_ai_description
import jasontoms.composeapp.generated.resources.work_my_part
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.AppForStoreLink
import theme.components.LinkBadge
import theme.components.LinkBadgeType
import theme.components.SelectableText
import theme.components.VerticalSpacer
import ui.ContentCard
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.WorkCard(
    title: String,
    location: String,
    years: String,
    aiDescription: String,
    myPart: String,
    logo: CdnImage,
    websiteUrl: String,
    appForStoreLink: AppForStoreLink?,
    placement: ContentCardPlacement,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    secondaryImage: CdnImage? = null,
) {
    WorkCard(
        modifier = modifier,
        title = title,
        location = location,
        years = years,
        aiDescription = aiDescription,
        myPart = myPart,
        logo = logo,
        websiteUrl = websiteUrl,
        appForStoreLink = appForStoreLink,
        placement = placement,
        backgroundColor = backgroundColor,
        borderBrush = SolidColor(borderColor),
        textColor = textColor,
        secondaryImage = secondaryImage,
    )
}

@Composable
fun ColumnScope.WorkCard(
    title: String,
    location: String,
    years: String,
    aiDescription: String,
    myPart: String,
    logo: CdnImage,
    websiteUrl: String,
    appForStoreLink: AppForStoreLink?,
    placement: ContentCardPlacement,
    backgroundColor: Color,
    borderBrush: Brush,
    textColor: Color,
    modifier: Modifier = Modifier,
    secondaryImage: CdnImage? = null,
) {
    ContentCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        borderBrush = borderBrush,
        image = { WorkCardImages(logo, secondaryImage) },
        details = {
            Column(modifier = Modifier.fillMaxWidth()) {
                SelectableText(
                    text = title,
                    color = textColor,
                    style = MaterialTheme.typography.displaySmall,
                )
                SelectableText(
                    text = location,
                    color = textColor,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.headlineSmall,
                )
                SelectableText(
                    text = years,
                    color = textColor,
                    style = MaterialTheme.typography.headlineSmall,
                )
                VerticalSpacer(Dimens.small)
                Text(
                    text = stringResource(Res.string.work_ai_description),
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
                SelectableText(
                    text = aiDescription,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                )
                VerticalSpacer(Dimens.xSmall)
                Text(
                    text = stringResource(Res.string.work_my_part),
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
                SelectableText(
                    text = myPart,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                )
                VerticalSpacer(Dimens.small)
                FlowRow(
                    itemVerticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dimens.small),
                    verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)
                ) {
                    appForStoreLink?.let {
                        LinkBadge(
                            modifier = Modifier.height(50.dp),
                            type = LinkBadgeType.PlayStore(appForStoreLink)
                        )
                    }
                    LinkBadge(
                        modifier = Modifier.height(50.dp),
                        type = LinkBadgeType.Website(websiteUrl)
                    )
                }
            }
        },
        placement = placement,
    )
}

@Composable
private fun WorkCardImages(logo: CdnImage, secondaryImage: CdnImage?) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    if (widthClass == WindowWidthSizeClass.Expanded) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.medium, Alignment.CenterVertically)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.medium)
                    .clip(MaterialTheme.shapes.medium),
                model = logo.url,
                contentDescription = null,
            )
            secondaryImage?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.medium)
                        .clip(MaterialTheme.shapes.medium),
                    model = it.url,
                    contentDescription = null,
                )
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimens.medium)
                    .clip(MaterialTheme.shapes.medium),
                model = logo.url,
                contentDescription = null,
            )
            secondaryImage?.let {
                AsyncImage(
                    modifier = Modifier
                        .weight(1f)
                        .padding(Dimens.medium)
                        .clip(MaterialTheme.shapes.medium),
                    model = it.url,
                    contentDescription = null,
                )
            }
        }
    }
}

@Previews
@Composable
private fun WorkCardPreview() {
    ContentPreview {
        WorkCard(
            title = "Job Title",
            location = "Location, USA",
            years = "August 2020 - September 2024",
            aiDescription = "This is a description of the job role, responsibilities, and achievements.",
            myPart = "Key tasks and projects handled during the tenure.",
            logo = CdnImage.PICKY_ICON,
            websiteUrl = "https://example.com",
            appForStoreLink = AppForStoreLink.BANKID,
            placement = ContentCardPlacement.START,
            backgroundColor = Color.Gray,
            borderColor = Color.DarkGray,
            textColor = Color.White
        )
        WorkCard(
            title = "Job Title",
            location = "Location",
            years = "2020 - 2024",
            aiDescription = "This is a description of the job role, responsibilities, and achievements.",
            myPart = "Key tasks and projects handled during the tenure.",
            logo = CdnImage.PICKY_ICON,
            websiteUrl = "https://example.com",
            appForStoreLink = AppForStoreLink.BANKID,
            placement = ContentCardPlacement.END,
            backgroundColor = Color.Gray,
            borderColor = Color.DarkGray,
            textColor = Color.White
        )
    }
}