package theme.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import theme.Previews
import kotlin.math.PI
import kotlin.math.sin

@Previews
@Composable
fun WebsiteBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val pulseAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 15000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val baseColor = MaterialTheme.colorScheme.background
    val accentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(baseColor)
    ) {
        drawTexturedBackground(
            pulseValue = pulseAnimation,
            baseColor = baseColor,
            accentColor = accentColor,
        )
    }
}

private fun DrawScope.drawTexturedBackground(
    pulseValue: Float,
    baseColor: Color,
    accentColor: Color,
) {
    val width = size.width
    val height = size.height

    val gradientBrush = Brush.radialGradient(
        colors = listOf(
            accentColor.copy(alpha = 0.05f),
            baseColor
        ),
        center = Offset(width * 0.5f, height * 0.3f),
        radius = width * 0.8f
    )

    drawRect(
        brush = gradientBrush,
        size = size
    )

    val dotSpacing = 10.dp.toPx()
    val baseDotRadius = 1.dp.toPx()
    val baseDotAlpha = 0.2f

    val cols = (width / dotSpacing).toInt() + 1
    val rows = (height / dotSpacing).toInt() + 1

    for (col in 0..cols) {
        for (row in 0..rows) {
            val x = col * dotSpacing
            val y = row * dotSpacing

            val wave = sin((x + y) * 0.02f + pulseValue * PI * 2f)
            val waveFactor = (wave * 0.5f + 0.5f).toFloat()
            val currentRadius = baseDotRadius * (1f + waveFactor * 0.5f)
            val currentAlpha = baseDotAlpha * (1f + waveFactor)

            drawCircle(
                color = accentColor.copy(alpha = currentAlpha),
                radius = currentRadius,
                center = Offset(x, y)
            )
        }
    }
}
