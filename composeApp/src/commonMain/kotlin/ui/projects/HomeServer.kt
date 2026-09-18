package ui.projects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.project_2_description
import jasontoms.composeapp.generated.resources.project_2_title
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Previews
import theme.dockerBlue
import theme.linuxMintGreen
import ui.portfolio.BrandAccent
import ui.portfolio.CardBodyText
import ui.portfolio.Logo
import ui.portfolio.LogoTiles
import ui.portfolio.PortfolioCard
import utils.CdnImage

@Composable
fun HomeServer(modifier: Modifier = Modifier) {
    PortfolioCard(
        modifier = modifier,
        accent = BrandAccent(linuxMintGreen, dockerBlue),
        title = stringResource(Res.string.project_2_title),
        media = { size ->
            LogoTiles(Logo(CdnImage.LINUX_MINT_ICON, dockerBlue), Logo(CdnImage.DOCKER_ICON, dockerBlue), size = size)
        },
    ) {
        CardBodyText(stringResource(Res.string.project_2_description))
    }
}

@Previews
@Composable
private fun HomeServerPreview() {
    ContentPreview {
        HomeServer()
    }
}
