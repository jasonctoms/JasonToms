package theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FlippableCard(
    cardModifier: Modifier = Modifier,
    frontCardContent: @Composable () -> Unit,
    frontCardConfig: CardConfig = CardConfig(colors = CardDefaults.cardColors()),
    backCardContent: @Composable () -> Unit,
    backCardConfig: CardConfig = CardConfig(colors = CardDefaults.cardColors()),
    flipDirection: FlipDirection = FlipDirection.HORIZONTAL,
    cardFlipping: (faceUp: Boolean) -> Unit = {},
) {
    var faceUp by remember { mutableStateOf(true) }
    DoubleSidedLayout(
        faceUp = faceUp,
        front = {
            Card(
                modifier = cardModifier,
                colors = frontCardConfig.colors,
                border = frontCardConfig.border,
                onClick = {
                    faceUp = !faceUp
                    cardFlipping(faceUp)
                }
            ) {
                frontCardContent()
            }
        },
        back = {
            Card(
                // since the box in the layout is rotated, we need to un-rotate the content
                modifier = cardModifier.graphicsLayer {
                    when (flipDirection) {
                        FlipDirection.HORIZONTAL -> rotationY = -180f
                        FlipDirection.VERTICAL -> rotationX = -180f
                    }
                },
                colors = backCardConfig.colors,
                border = backCardConfig.border,
                onClick = {
                    faceUp = !faceUp
                    cardFlipping(faceUp)
                },
            ) {
                backCardContent()
            }
        },
        flipDirection = flipDirection,
    )
}

data class CardConfig(val colors: CardColors, val border: BorderStroke? = null)