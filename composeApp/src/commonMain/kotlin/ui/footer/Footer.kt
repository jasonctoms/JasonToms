package ui.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import getPlatform
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.footer
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.see_on_github
import navigation.Route
import navigation.TopLevelDestination
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.components.ContentColumn
import theme.components.HorizontalSpacer
import theme.components.SelectableText
import theme.components.SocialLinks
import ui.navigation.Brand

@Composable
fun Footer(onNavigate: (Route) -> Unit, modifier: Modifier = Modifier) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.92f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        ContentColumn(modifier = Modifier.padding(vertical = Dimens.large)) {
            if (compact) {
                FooterIdentity()
                FooterLinks(onNavigate = onNavigate)
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    FooterIdentity()
                    FooterLinks(onNavigate = onNavigate)
                }
            }
        }
    }
}

@Composable
private fun FooterIdentity() {
    val platform = remember { getPlatform() }
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.xSmall)) {
        Brand(showName = true)
        SelectableText(
            text = stringResource(Res.string.footer, platform.name),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
private fun FooterLinks(onNavigate: (Route) -> Unit) {
    val uriHandler = LocalUriHandler.current
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Dimens.xxSmall),
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        TopLevelDestination.entries.forEach { destination ->
            TextButton(
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                onClick = { onNavigate(destination.route) },
            ) {
                Text(text = stringResource(destination.label))
            }
        }
        TextButton(
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
            onClick = { uriHandler.openUri("https://github.com/jasonctoms/JasonToms") },
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_github),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            HorizontalSpacer(Dimens.xxSmall)
            Text(text = stringResource(Res.string.see_on_github))
        }
        SocialLinks()
    }
}
