package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_4_description
import jasontoms.composeapp.generated.resources.work_4_location
import jasontoms.composeapp.generated.resources.work_4_tasks
import jasontoms.composeapp.generated.resources.work_4_title
import jasontoms.composeapp.generated.resources.work_4_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.AppForStoreLink
import theme.vippsBlue
import theme.vippsOrange
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Vipps(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_4_title),
        location = stringResource(Res.string.work_4_location),
        years = stringResource(Res.string.work_4_years),
        aiDescription = stringResource(Res.string.work_4_description),
        myPart = stringResource(Res.string.work_4_tasks),
        logo = Logo(CdnImage.VIPPS_LOGO, vippsOrange, fillsTile = true),
        accent = BrandAccent(vippsOrange, vippsBlue),
        websiteUrl = "https://vipps.no/",
        appForStoreLink = AppForStoreLink.VIPPS,
    )
}

@Previews
@Composable
private fun VippsCardPreview() {
    ContentPreview {
        Vipps()
    }
}
