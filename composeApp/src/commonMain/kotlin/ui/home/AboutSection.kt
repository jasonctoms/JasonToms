package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.about_title
import jasontoms.composeapp.generated.resources.bio
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.components.SelectableText
import ui.Section
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * The bio, with the portfolio callout next to it on wide screens and underneath it on narrow ones.
 */
@Composable
fun AboutSection(onExplorePortfolio: () -> Unit, modifier: Modifier = Modifier) {
    val careerYears = rememberCareerYears()
    Section(title = stringResource(Res.string.about_title), modifier = modifier) {
        if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Expanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.large),
                verticalAlignment = Alignment.Top,
            ) {
                BiographyText(careerYears = careerYears, modifier = Modifier.weight(3f))
                PortfolioCallout(
                    careerYears = careerYears,
                    onClick = onExplorePortfolio,
                    modifier = Modifier.weight(2f),
                )
            }
        } else {
            BiographyText(careerYears = careerYears)
            PortfolioCallout(careerYears = careerYears, onClick = onExplorePortfolio)
        }
    }
}

@Composable
private fun BiographyText(careerYears: CareerYears, modifier: Modifier = Modifier) {
    SelectableText(
        modifier = modifier,
        text = stringResource(Res.string.bio, careerYears.makingApps, careerYears.asEngineer),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

data class CareerYears(val makingApps: Int, val asEngineer: Int)

@OptIn(ExperimentalTime::class)
@Composable
fun rememberCareerYears(): CareerYears = remember {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val graduation = LocalDate(2013, 5, 17)
    val firstAndroidJob = LocalDate(2016, 8, 1)
    CareerYears(
        makingApps = (today - firstAndroidJob).years,
        asEngineer = (today - graduation).years,
    )
}

@Previews
@Composable
private fun AboutSectionPreview() {
    ContentPreview {
        AboutSection(onExplorePortfolio = {})
    }
}
