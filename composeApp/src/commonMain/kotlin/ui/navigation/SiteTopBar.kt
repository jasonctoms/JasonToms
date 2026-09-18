package ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import navigation.Route
import navigation.TopLevelDestination
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.Previews
import theme.pageGutter

/**
 * The site-wide navigation bar. It lives outside of the NavDisplay so it stays put while the
 * pages underneath it transition.
 */
@Composable
fun SiteTopBar(
    currentRoute: Route,
    onNavigate: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.88f))
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)),
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .widthIn(max = Dimens.maxContentWidth + pageGutter() * 2)
                    .fillMaxWidth()
                    .height(Dimens.topBarHeight)
                    .padding(horizontal = pageGutter()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Brand(
                    modifier = Modifier
                        .clip(CircleShape)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable(role = Role.Button) { onNavigate(Route.Home) },
                    showName = !compact,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.xxSmall)) {
                    TopLevelDestination.entries.forEach { destination ->
                        NavPill(
                            label = stringResource(destination.label),
                            selected = currentRoute == destination.route,
                            onClick = { onNavigate(destination.route) },
                        )
                    }
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
    }
}

@Composable
private fun NavPill(label: String, selected: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val colors = MaterialTheme.colorScheme
    val background by animateColorAsState(
        when {
            selected -> colors.secondaryContainer
            hovered -> colors.surfaceContainerHighest
            else -> Color.Transparent
        }
    )
    val content by animateColorAsState(if (selected) colors.onSecondaryContainer else colors.onSurfaceVariant)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .selectable(
                selected = selected,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .padding(horizontal = Dimens.small, vertical = Dimens.xSmall + Dimens.xxSmall),
    ) {
        Text(text = label, style = MaterialTheme.typography.labelLarge, color = content)
    }
}

@Previews
@Composable
private fun SiteTopBarPreview() {
    AppTheme {
        SiteTopBar(currentRoute = Route.Home, onNavigate = {})
    }
}
