package ui.portfolio

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import theme.Dimens
import theme.LocalWindowSizeClass
import theme.components.SelectableText

/** A brand's two main colors */
data class BrandAccent(val primary: Color, val secondary: Color = primary)

private val sideColumnWidth = 240.dp

/**
 * The card used for every entry on the portfolio page.
 *
 * The card itself is always the theme's surface color, so text is always in the site's own
 * colors. The brand only shows up as soft corner glows, a thin gradient border, and optionally
 * a heavily faded photo, which keeps six very different brands from fighting each other.
 *
 * On wide screens the logo, title, details and links sit in a narrow side column, centered both
 * horizontally and against the body text, which gets the rest of the width. On tablets the logo
 * sits beside the title above the body, and on phones everything stacks.
 *
 * @param media the logo or icon, drawn at the size it is given
 * @param subtitle usually a location
 * @param period usually the dates, shown as a small pill
 */
@Composable
fun PortfolioCard(
    accent: BrandAccent,
    title: String,
    media: @Composable (size: Dp) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    period: String? = null,
    backgroundImage: DrawableResource? = null,
    links: (@Composable FlowRowScope.() -> Unit)? = null,
    body: @Composable ColumnScope.() -> Unit,
) {
    val widthClass = LocalWindowSizeClass.current.widthSizeClass
    val shape = MaterialTheme.shapes.extraLarge
    val isDark = MaterialTheme.colorScheme.surface.luminance() < 0.5f
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val lift by animateDpAsState(if (hovered) Dimens.xxSmall else 0.dp)
    val borderAlpha by animateFloatAsState(if (hovered) 0.9f else 0.45f)
    // brand colors are tuned for white backgrounds, so they need a little more strength on dark ones
    val glowAlpha = if (isDark) 0.26f else 0.16f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .hoverable(interactionSource)
            .graphicsLayer { translationY = -lift.toPx() }
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.94f))
            .drawBehind {
                drawRect(
                    Brush.radialGradient(
                        colors = listOf(accent.primary.copy(alpha = glowAlpha), Color.Transparent),
                        center = Offset.Zero,
                        radius = size.maxDimension * 0.6f,
                    )
                )
                drawRect(
                    Brush.radialGradient(
                        colors = listOf(accent.secondary.copy(alpha = glowAlpha), Color.Transparent),
                        center = Offset(size.width, size.height),
                        radius = size.maxDimension * 0.5f,
                    )
                )
            }
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(accent.primary.copy(alpha = borderAlpha), accent.secondary.copy(alpha = borderAlpha))
                ),
                shape = shape,
            ),
    ) {
        backgroundImage?.let { FadedBackgroundImage(it, alpha = if (isDark) 0.3f else 0.22f) }

        if (widthClass == WindowWidthSizeClass.Expanded) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(Dimens.large),
                horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.width(sideColumnWidth),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.small),
                ) {
                    media(72.dp)
                    CardHeading(title = title, subtitle = subtitle, period = period, centered = true)
                    links?.let { CardLinks(it, centered = true) }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimens.small),
                    content = body,
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (widthClass == WindowWidthSizeClass.Compact) Dimens.medium - Dimens.xxSmall else Dimens.medium),
                verticalArrangement = Arrangement.spacedBy(Dimens.small),
            ) {
                if (widthClass == WindowWidthSizeClass.Compact) {
                    // phones are too narrow to fit two logos beside a long title, so stack them
                    media(56.dp)
                    CardHeading(title = title, subtitle = subtitle, period = period)
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        media(72.dp)
                        CardHeading(
                            modifier = Modifier.weight(1f),
                            title = title,
                            subtitle = subtitle,
                            period = period,
                        )
                    }
                }
                body()
                links?.let { CardLinks(it) }
            }
        }
    }
}

@Composable
private fun CardHeading(
    title: String,
    subtitle: String?,
    period: String?,
    modifier: Modifier = Modifier,
    centered: Boolean = false,
) {
    val compact = LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact
    val textAlign = if (centered) TextAlign.Center else TextAlign.Start
    Column(
        modifier = modifier,
        horizontalAlignment = if (centered) Alignment.CenterHorizontally else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(Dimens.xxSmall),
    ) {
        SelectableText(
            text = title,
            textAlign = textAlign,
            style = if (compact) MaterialTheme.typography.headlineSmall else MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        subtitle?.let {
            SelectableText(
                text = it,
                textAlign = textAlign,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        period?.let {
            Text(
                modifier = Modifier
                    .padding(top = Dimens.xxSmall)
                    .clip(MaterialTheme.shapes.extraLarge)
                    // neutral rather than brand-tinted: a tint of a dark brand color vanishes on a dark card
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.extraLarge)
                    .padding(horizontal = Dimens.small - Dimens.xxSmall, vertical = Dimens.xxSmall),
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun CardLinks(links: @Composable FlowRowScope.() -> Unit, centered: Boolean = false) {
    FlowRow(
        modifier = Modifier.padding(top = Dimens.xSmall),
        horizontalArrangement = if (centered) {
            Arrangement.spacedBy(Dimens.xSmall, Alignment.CenterHorizontally)
        } else {
            Arrangement.spacedBy(Dimens.xSmall)
        },
        verticalArrangement = Arrangement.spacedBy(Dimens.xSmall),
        itemVerticalAlignment = Alignment.CenterVertically,
        content = links,
    )
}

/** A photo that is barely there: faded out, and dissolving away from the top-right corner. */
@Composable
private fun BoxScope.FadedBackgroundImage(image: DrawableResource, alpha: Float) {
    Image(
        modifier = Modifier
            .matchParentSize()
            .graphicsLayer {
                this.alpha = alpha
                // needed so the DstIn mask below only erases the image, not the card behind it
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        center = Offset(size.width, 0f),
                        radius = size.maxDimension * 0.8f,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            },
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun CardBodyText(text: String, modifier: Modifier = Modifier) {
    SelectableText(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun CardLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = text.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
