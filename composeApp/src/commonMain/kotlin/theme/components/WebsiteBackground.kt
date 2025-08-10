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
import kotlin.math.sqrt

@Previews
@Composable
fun WebsiteBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val pulseAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 6000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
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

    val dotSpacing = 10.dp.toPx()
    val dotRadius = (pulseValue * 1.5f).dp.toPx()
    val dotAlpha = 0.3f + pulseValue * 0.2f

    val gradientBrush = Brush.radialGradient(
        colors = listOf(
            baseColor,
            accentColor.copy(alpha = dotAlpha * 0.5f),
            baseColor
        ),
        center = Offset(width * 0.5f, height * 0.3f),
        radius = width * 0.8f
    )

    drawRect(
        brush = gradientBrush,
        size = size
    )

    val cols = (width / dotSpacing).toInt() + 2
    val rows = (height / dotSpacing).toInt() + 2

    for (col in 0..cols) {
        for (row in 0..rows) {
            val x = col * dotSpacing + (if (row % 2 == 0) 0f else dotSpacing * 0.5f)
            val y = row * dotSpacing

            if (x <= width + dotSpacing && y <= height + dotSpacing) {
                val distance = sqrt(
                    (x - width * 0.5f) *
                            (x - width * 0.5f) + (y - height * 0.5f) *
                            (y - height * 0.5f)
                )
                val wave = sin(distance * 0.01f + pulseValue * PI * 2) * 0.3f + 0.7f
                val currentAlpha = dotAlpha * wave

                drawCircle(
                    color = accentColor.copy(alpha = currentAlpha.toFloat()),
                    radius = (dotRadius * wave).toFloat(),
                    center = Offset(x, y)
                )
            }
        }
    }

    // Add subtle noise overlay
    repeat(200) {
        val x = (0..width.toInt()).random().toFloat()
        val y = (0..height.toInt()).random().toFloat()
        val size = (0.5f + pulseValue * 0.5f).dp.toPx()

        drawCircle(
            color = accentColor.copy(alpha = 0.1f + pulseValue * 0.05f),
            radius = size,
            center = Offset(x, y)
        )
    }
}
