package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import theme.Dimens

@Composable
fun StartScreen(viewModel: StartScreenViewModel = viewModel { StartScreenViewModel() }) {
    val viewState by viewModel.viewState.asState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.medium),
        contentPadding = PaddingValues(Dimens.small)
    ) {
        item {
            TemporaryDisclaimer()
        }

        items(viewState.items) { item ->
            when (item) {
                StartScreenItem.ProfilePhoto -> ProfilePhoto()
                StartScreenItem.Biography -> BiographyCard(modifier = Modifier.widthIn(max = 1000.dp))
                is StartScreenItem.Project -> TODO()
                StartScreenItem.Footer -> Footer()
            }
        }
    }
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
