package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_3_description
import jasontoms.composeapp.generated.resources.work_3_location
import jasontoms.composeapp.generated.resources.work_3_tasks
import jasontoms.composeapp.generated.resources.work_3_title
import jasontoms.composeapp.generated.resources.work_3_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.AppForStoreLink
import theme.nrkDarkBlue
import theme.nrkLightBlue
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Nrk(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_3_title),
        location = stringResource(Res.string.work_3_location),
        years = stringResource(Res.string.work_3_years),
        aiDescription = stringResource(Res.string.work_3_description),
        myPart = stringResource(Res.string.work_3_tasks),
        logo = Logo(CdnImage.NRK_TV_LOGO, nrkDarkBlue),
        accent = BrandAccent(nrkLightBlue, nrkDarkBlue),
        websiteUrl = "https://tv.nrk.no/",
        appForStoreLink = AppForStoreLink.NRK_TV,
    )
}

@Previews
@Composable
private fun NrkCardPreview() {
    ContentPreview {
        Nrk()
    }
}
