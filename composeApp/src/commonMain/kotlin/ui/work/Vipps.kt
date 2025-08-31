package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_4_description
import jasontoms.composeapp.generated.resources.work_4_location
import jasontoms.composeapp.generated.resources.work_4_tasks
import jasontoms.composeapp.generated.resources.work_4_title
import jasontoms.composeapp.generated.resources.work_4_years
import org.jetbrains.compose.resources.stringResource
import theme.components.AppForStoreLink
import theme.vippsBlue
import theme.vippsOrange
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Vipps(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_4_title),
        location = stringResource(Res.string.work_4_location),
        years = stringResource(Res.string.work_4_years),
        aiDescription = stringResource(Res.string.work_4_description),
        myPart = stringResource(Res.string.work_4_tasks),
        logo = CdnImage.VIPPS_LOGO,
        websiteUrl = "https://vipps.no/",
        appForStoreLink = AppForStoreLink.VIPPS,
        placement = placement,
        backgroundColor = vippsOrange,
        borderColor = vippsBlue,
        textColor = Color.White,
    )
}