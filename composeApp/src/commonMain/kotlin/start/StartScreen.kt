package start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun StartScreen(viewModel: StartScreenViewModel = viewModel { StartScreenViewModel() }) {
    val viewState by viewModel.viewState.asState()
    StartScreenContent(
        buttonClick = { viewModel.togglePicture() },
        showImage = viewState.showPicture,
        greeting = viewState.greeting
    )
}

@Composable
private fun StartScreenContent(buttonClick: () -> Unit, showImage: Boolean, greeting: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = buttonClick) {
            Text("Click me!")
        }
        AnimatedVisibility(showImage) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text(text = greeting)
            }
        }
    }

}