package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_1_description
import jasontoms.composeapp.generated.resources.work_1_location
import jasontoms.composeapp.generated.resources.work_1_tasks
import jasontoms.composeapp.generated.resources.work_1_title
import jasontoms.composeapp.generated.resources.work_1_years
import org.jetbrains.compose.resources.stringResource
import theme.kongsbergRed
import theme.kongsbergYellow
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Kongsberg(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_1_title),
        location = stringResource(Res.string.work_1_location),
        years = stringResource(Res.string.work_1_years),
        aiDescription = stringResource(Res.string.work_1_description),
        myPart = stringResource(Res.string.work_1_tasks),
        logo = CdnImage.KONGSBERG_LOGO,
        secondaryImage = CdnImage.KMASTER,
        websiteUrl = "https://www.kongsberg.com/maritime/products/positioning-and-manoeuvring/dynamic-positioning/",
        appForStoreLink = null,
        placement = placement,
        backgroundColor = kongsbergRed,
        borderColor = kongsbergYellow,
        textColor = Color.White,
    )
}