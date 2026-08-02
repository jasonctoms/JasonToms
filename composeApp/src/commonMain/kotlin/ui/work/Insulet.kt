package ui.work

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_6_description
import jasontoms.composeapp.generated.resources.work_6_location
import jasontoms.composeapp.generated.resources.work_6_tasks
import jasontoms.composeapp.generated.resources.work_6_title
import jasontoms.composeapp.generated.resources.work_6_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.insuletPurple
import theme.omnipodOrange
import ui.ContentCardPlacement
import utils.CdnImage

@Composable
fun ColumnScope.Insulet(placement: ContentCardPlacement, modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_6_title),
        location = stringResource(Res.string.work_6_location),
        years = stringResource(Res.string.work_6_years),
        aiDescription = stringResource(Res.string.work_6_description),
        myPart = stringResource(Res.string.work_6_tasks),
        logo = CdnImage.INSULET_LOGO,
        secondaryImage = CdnImage.OMNIPOD_LOGO,
        websiteUrl = "https://www.insulet.com/",
        appForStoreLink = null,
        placement = placement,
        backgroundColor = Color.White,
        borderBrush = Brush.sweepGradient(
            colors = listOf(
                insuletPurple,
                omnipodOrange,
                insuletPurple
            )
        ),
        textColor = Color.Black,
    )
}

@Previews
@Composable
private fun InsuletCardPreview() {
    ContentPreview {
        Insulet(placement = ContentCardPlacement.START)
    }
}