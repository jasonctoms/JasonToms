package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.important_things
import jasontoms.composeapp.generated.resources.things_i_have_worked_on
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import utils.VerticalSpacer

@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.medium),
    ) {
        ScreenHeader()
        SectionHeader(stringResource(Res.string.important_things))
        ImportantThings()
        SectionHeader(stringResource(Res.string.things_i_have_worked_on))
        VerticalSpacer(Dimens.large)
        Text(text = "SECTION UNDER CONSTRUCTION", color = MaterialTheme.colorScheme.onBackground)
        VerticalSpacer(Dimens.extraLarge)
        TemporaryDisclaimer()
        Footer()
    }
}

@Composable
private fun SectionHeader(header: String) {
    Text(
        modifier = Modifier.padding(horizontal = Dimens.medium),
        text = header,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
private fun TemporaryDisclaimer() {
    Text(
        modifier = Modifier.sizeIn(maxWidth = 400.dp),
        text = "Disclaimer: This page is currently under construction and a playground to learn how Kotlin " +
                "Multiplatform Wasm works. If you want to help make it prettier or give some design ideas, feel free " +
                "to contribute or start a discussion with the GitHub link at the bottom of the page.",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
    )
}
