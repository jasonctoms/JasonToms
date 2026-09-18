package ui.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.hero_cta
import jasontoms.composeapp.generated.resources.portfolio_callout_body
import jasontoms.composeapp.generated.resources.portfolio_callout_eyebrow
import jasontoms.composeapp.generated.resources.portfolio_callout_title
import jasontoms.composeapp.generated.resources.stat_years_apps
import jasontoms.composeapp.generated.resources.stat_years_engineer
import org.jetbrains.compose.resources.stringResource
import theme.ContentPreview
import theme.Dimens
import theme.Previews
import ui.components.BigButton

/**
 * A big, colorful card that sends people to the portfolio page. The whole card is clickable, not
 * just the button, and it lifts slightly when hovered so it feels interactive on desktop.
 */
@Composable
fun PortfolioCallout(
    careerYears: CareerYears,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val shape = MaterialTheme.shapes.extraLarge
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val lift by animateDpAsState(if (hovered) Dimens.xSmall else 0.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = -lift.toPx() }
            .background(
                brush = Brush.linearGradient(listOf(colors.primaryContainer, colors.tertiaryContainer)),
                shape = shape,
            )
            .border(width = 1.dp, color = colors.primary.copy(alpha = 0.3f), shape = shape)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
            .padding(Dimens.large),
        verticalArrangement = Arrangement.spacedBy(Dimens.small),
    ) {
        Text(
            text = stringResource(Res.string.portfolio_callout_eyebrow).uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = colors.primary,
        )
        Text(
            text = stringResource(Res.string.portfolio_callout_title),
            style = MaterialTheme.typography.headlineMedium,
            color = colors.onPrimaryContainer,
        )
        Text(
            text = stringResource(Res.string.portfolio_callout_body),
            style = MaterialTheme.typography.bodyLarge,
            color = colors.onPrimaryContainer,
        )
        Row(
            modifier = Modifier.padding(vertical = Dimens.xSmall),
            horizontalArrangement = Arrangement.spacedBy(Dimens.large),
        ) {
            Stat(value = careerYears.makingApps, label = stringResource(Res.string.stat_years_apps))
            Stat(value = careerYears.asEngineer, label = stringResource(Res.string.stat_years_engineer))
        }
        BigButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.hero_cta),
            onClick = onClick,
        )
    }
}

@Composable
private fun Stat(value: Int, label: String) {
    Column {
        Text(
            text = "$value",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
        )
    }
}

@Previews
@Composable
private fun PortfolioCalloutPreview() {
    ContentPreview {
        PortfolioCallout(careerYears = CareerYears(makingApps = 10, asEngineer = 13), onClick = {})
    }
}
