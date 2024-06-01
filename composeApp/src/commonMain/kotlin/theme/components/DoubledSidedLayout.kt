package theme.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.Dimens

const val CARD_FLIP_DURATION = 350

@Composable
fun DoubleSidedLayout(
    faceUp: Boolean,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val rotation by animateFloatAsState(
        targetValue = if (faceUp) 0f else 180f,
        animationSpec = tween(durationMillis = CARD_FLIP_DURATION),
    )

    // we do not want to switch the content until the flip is half way done and both sides are "invisible"
    val frontVisible by derivedStateOf { rotation in 0f..90f }

    Box(modifier = modifier.graphicsLayer { rotationX = rotation }) {
        if (frontVisible) front() else back()
    }
}

@Preview
@Composable
fun TestFlip() {
    var faceUp by remember { mutableStateOf(true) }
    DoubleSidedLayout(
        faceUp = faceUp,
        front = {
            Card(onClick = { faceUp = !faceUp }) {
                Text(modifier = Modifier.padding(Dimens.medium), text = "Front")
            }
        },
        back = {
            Card(modifier = Modifier.graphicsLayer { rotationX = -180f }, onClick = { faceUp = !faceUp }) {
                Text(modifier = Modifier.padding(Dimens.medium), text = "Back")
            }
        },
    )
}
