package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import getPlatform
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.footer
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.see_on_github
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import utils.ExpandingSpacer
import utils.HorizontalSpacer
import utils.VerticalSpacer

@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(Dimens.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.sizeIn(maxWidth = 400.dp),
            text = "Disclaimer: This page is currently under construction and a playground to learn how Kotlin " +
                    "Multiplatform works. If you want to help make it prettier or give some design ideas, feel free to " +
                    "contribute or start a discussion with the GitHub link at the bottom of the page.",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
        VerticalSpacer(Dimens.medium)
        BiographyCard(modifier = Modifier.fillMaxWidth(0.6f))
        ExpandingSpacer()
        Footer()
    }
}

@Composable
private fun Footer() {
    val uriHandler = LocalUriHandler.current
    val platform = remember { getPlatform() }
    Text(
        text = stringResource(Res.string.footer, platform.name),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelSmall
    )
    TextButton(onClick = { uriHandler.openUri("https://github.com/jasonctoms/JasonToms") }) {
        Icon(
            painter = painterResource(Res.drawable.ic_github),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
        HorizontalSpacer(Dimens.xxSmall)
        Text(text = stringResource(Res.string.see_on_github), color = MaterialTheme.colorScheme.onBackground)
    }
}