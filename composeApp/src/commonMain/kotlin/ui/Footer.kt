package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalUriHandler
import getPlatform
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.footer
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.see_on_github
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import utils.HorizontalSpacer
import utils.VerticalSpacer

@Composable
fun Footer() {
    val uriHandler = LocalUriHandler.current
    val platform = remember { getPlatform() }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        VerticalSpacer(Dimens.large)
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
}