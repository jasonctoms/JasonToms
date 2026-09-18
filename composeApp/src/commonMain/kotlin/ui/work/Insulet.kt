package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Insulet(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_6_title),
        location = stringResource(Res.string.work_6_location),
        years = stringResource(Res.string.work_6_years),
        aiDescription = stringResource(Res.string.work_6_description),
        myPart = stringResource(Res.string.work_6_tasks),
        logo = Logo(CdnImage.INSULET_LOGO, Color.White),
        secondaryLogo = Logo(CdnImage.OMNIPOD_LOGO, Color.White),
        accent = BrandAccent(insuletPurple, omnipodOrange),
        websiteUrl = "https://www.insulet.com/",
        appForStoreLink = null,
    )
}

@Previews
@Composable
private fun InsuletCardPreview() {
    ContentPreview {
        Insulet()
    }
}
