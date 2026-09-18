package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_5_description
import jasontoms.composeapp.generated.resources.work_5_location
import jasontoms.composeapp.generated.resources.work_5_tasks
import jasontoms.composeapp.generated.resources.work_5_title
import jasontoms.composeapp.generated.resources.work_5_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.AppForStoreLink
import theme.stuhBackground
import theme.stuhGreen
import theme.stuhPurple
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Stuh(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_5_title),
        location = stringResource(Res.string.work_5_location),
        years = stringResource(Res.string.work_5_years),
        aiDescription = stringResource(Res.string.work_5_description),
        myPart = stringResource(Res.string.work_5_tasks),
        logo = Logo(CdnImage.STUH_LOGO, stuhBackground),
        secondaryLogo = Logo(CdnImage.BANKID_LOGO, stuhBackground),
        accent = BrandAccent(stuhPurple, stuhGreen),
        websiteUrl = "https://stoe.no/",
        appForStoreLink = AppForStoreLink.BANKID,
    )
}

@Previews
@Composable
private fun StuhCardPreview() {
    ContentPreview {
        Stuh()
    }
}
