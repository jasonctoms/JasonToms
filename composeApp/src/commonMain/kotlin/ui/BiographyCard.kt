package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.bio
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.Dimens

val profileImageSize = 200.dp

@Composable
fun BiographyCard(modifier: Modifier = Modifier) {
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
    Card(modifier = modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(Dimens.medium)
        ) {
            Text(
                text = stringResource(Res.string.bio, yearsAsAndroidDeveloper, yearsAsEngineer),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun BiographyCardPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(Dimens.small)
        ) {
            BiographyCard()
        }
    }
}