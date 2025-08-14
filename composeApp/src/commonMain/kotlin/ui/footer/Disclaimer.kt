package ui.footer

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.components.SelectableText

@Composable
fun TemporaryDisclaimer(modifier: Modifier = Modifier) {
    SelectableText(
        modifier = modifier.sizeIn(maxWidth = 400.dp),
        text = "Disclaimer: This page is currently under construction and a playground to learn how Kotlin " +
                "Multiplatform Wasm works. If you want to help make it prettier or give some design ideas, feel free " +
                "to contribute or start a discussion with the GitHub link at the bottom of the page.",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
    )
}