package start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import utils.ImageUrls

@Composable
fun StartScreen(viewModel: StartScreenViewModel = viewModel { StartScreenViewModel() }) {
    val viewState by viewModel.viewState.asState()
    StartScreenContent(
        buttonClick = { viewModel.togglePicture() },
        showImage = viewState.showImage,
        greeting = viewState.greeting
    )
}

@Composable
private fun StartScreenContent(buttonClick: () -> Unit, showImage: Boolean, greeting: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = buttonClick) {
            Text("Hello!")
        }
        AnimatedVisibility(showImage) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(modifier = Modifier.size(300.dp), model = ImageUrls.PROFILE.url, contentDescription = null)
                Text(text = greeting, color = MaterialTheme.colorScheme.onBackground)
                Text(
                    text = "This will eventually become a real website!",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}