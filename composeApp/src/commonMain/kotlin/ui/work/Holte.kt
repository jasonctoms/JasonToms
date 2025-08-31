package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_2_description
import jasontoms.composeapp.generated.resources.work_2_location
import jasontoms.composeapp.generated.resources.work_2_tasks
import jasontoms.composeapp.generated.resources.work_2_title
import jasontoms.composeapp.generated.resources.work_2_years
import org.jetbrains.compose.resources.stringResource
import theme.components.AppForStoreLink
import theme.holteGreen
import theme.holteOrange
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Holte(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_2_title),
        location = stringResource(Res.string.work_2_location),
        years = stringResource(Res.string.work_2_years),
        aiDescription = stringResource(Res.string.work_2_description),
        myPart = stringResource(Res.string.work_2_tasks),
        logo = CdnImage.HOLTE_LOGO,
        websiteUrl = "https://egsoftware.com/no/byggebransjen/eg-holteportalen",
        appForStoreLink = AppForStoreLink.HOLTE,
        placement = placement,
        backgroundColor = holteGreen,
        borderColor = holteOrange,
        textColor = Color.White,
    )
}