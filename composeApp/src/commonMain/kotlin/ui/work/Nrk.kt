package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_3_description
import jasontoms.composeapp.generated.resources.work_3_location
import jasontoms.composeapp.generated.resources.work_3_tasks
import jasontoms.composeapp.generated.resources.work_3_title
import jasontoms.composeapp.generated.resources.work_3_years
import org.jetbrains.compose.resources.stringResource
import theme.components.AppForStoreLink
import theme.nrkDarkBlue
import theme.nrkLightBlue
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Nrk(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_3_title),
        location = stringResource(Res.string.work_3_location),
        years = stringResource(Res.string.work_3_years),
        aiDescription = stringResource(Res.string.work_3_description),
        myPart = stringResource(Res.string.work_3_tasks),
        logo = CdnImage.NRK_TV_LOGO,
        websiteUrl = "https://tv.nrk.no/",
        appForStoreLink = AppForStoreLink.NRK_TV,
        placement = placement,
        backgroundColor = nrkDarkBlue,
        borderColor = nrkLightBlue,
        textColor = Color.White,
    )
}