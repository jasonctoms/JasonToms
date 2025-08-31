package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_5_description
import jasontoms.composeapp.generated.resources.work_5_location
import jasontoms.composeapp.generated.resources.work_5_tasks
import jasontoms.composeapp.generated.resources.work_5_title
import jasontoms.composeapp.generated.resources.work_5_years
import org.jetbrains.compose.resources.stringResource
import theme.components.AppForStoreLink
import theme.stuhBackground
import theme.stuhGreen
import theme.stuhText
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Stuh(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_5_title),
        location = stringResource(Res.string.work_5_location),
        years = stringResource(Res.string.work_5_years),
        aiDescription = stringResource(Res.string.work_5_description),
        myPart = stringResource(Res.string.work_5_tasks),
        logo = CdnImage.STUH_LOGO,
        secondaryImage = CdnImage.BANKID_LOGO,
        websiteUrl = "https://stoe.no/",
        appForStoreLink = AppForStoreLink.BANKID,
        placement = placement,
        backgroundColor = stuhBackground,
        borderColor = stuhGreen,
        textColor = stuhText,
    )
}