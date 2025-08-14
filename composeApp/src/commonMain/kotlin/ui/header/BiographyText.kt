package ui.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.bio
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.components.SelectableText
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun BiographyText(modifier: Modifier = Modifier) {
    val today = remember {
        Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
    val yearsAsEngineer = remember(today) {
        val graduation = LocalDate(2013, 5, 17)
        (today - graduation).years
    }
    val yearsAsAndroidDeveloper = remember(today) {
        val firstAndroidJob = LocalDate(2016, 8, 1)
        (today - firstAndroidJob).years
    }

    SelectableText(
        modifier = modifier.padding(Dimens.medium),
        text = stringResource(Res.string.bio, yearsAsAndroidDeveloper, yearsAsEngineer),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}
