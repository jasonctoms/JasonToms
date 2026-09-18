package ui.work

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.work_1_description
import jasontoms.composeapp.generated.resources.work_1_location
import jasontoms.composeapp.generated.resources.work_1_tasks
import jasontoms.composeapp.generated.resources.work_1_title
import jasontoms.composeapp.generated.resources.work_1_years
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.kongsbergRed
import theme.kongsbergYellow
import ui.portfolio.BrandAccent
import ui.portfolio.Logo
import utils.CdnImage

@Composable
fun Kongsberg(modifier: Modifier = Modifier) {
    WorkCard(
        modifier = modifier,
        title = stringResource(Res.string.work_1_title),
        location = stringResource(Res.string.work_1_location),
        years = stringResource(Res.string.work_1_years),
        aiDescription = stringResource(Res.string.work_1_description),
        myPart = stringResource(Res.string.work_1_tasks),
        logo = Logo(CdnImage.KONGSBERG_LOGO, kongsbergRed),
        photos = listOf(CdnImage.KMASTER),
        accent = BrandAccent(kongsbergRed, kongsbergYellow),
        websiteUrl = "https://www.kongsberg.com/maritime/products/positioning-and-manoeuvring/dynamic-positioning/",
        appForStoreLink = null,
    )
}

@Previews
@Composable
private fun KongsbergCardPreview() {
    ContentPreview {
        Kongsberg()
    }
}
