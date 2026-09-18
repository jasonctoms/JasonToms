package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_2_description
import jasontoms.composeapp.generated.resources.work_2_location
import jasontoms.composeapp.generated.resources.work_2_tasks
import jasontoms.composeapp.generated.resources.work_2_title
import jasontoms.composeapp.generated.resources.work_2_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.components.AppForStoreLink
import theme.holteGreen
import theme.holteOrange
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Holte(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_2_title),
        location = stringResource(Res.string.work_2_location),
        years = stringResource(Res.string.work_2_years),
        aiDescription = stringResource(Res.string.work_2_description),
        myPart = stringResource(Res.string.work_2_tasks),
        logo = Logo(CdnImage.HOLTE_LOGO, holteGreen),
        accent = BrandAccent(holteOrange, holteGreen),
        websiteUrl = "https://egsoftware.com/no/byggebransjen/eg-holteportalen",
        appForStoreLink = AppForStoreLink.HOLTE,
    )
}

@Previews
@Composable
private fun HolteCardPreview() {
    ContentPreview {
        Holte()
    }
}
