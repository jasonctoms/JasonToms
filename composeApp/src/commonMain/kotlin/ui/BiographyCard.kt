package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.bio
import jasontoms.composeapp.generated.resources.derpdroid
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.ic_linkedin
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.Dimens
import utils.HorizontalSpacer
import utils.ImageUrls

val profileImageSize = 200.dp

@Composable
fun BiographyCard(modifier: Modifier = Modifier) {
    val showDerpdroid = remember { MutableTransitionState(false) }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        BiographyCardContent(onClick = { showDerpdroid.targetState = true })
        AnimatedVisibility(visibleState = showDerpdroid) {
            Image(
                modifier = Modifier.rotate(90f).size(profileImageSize),
                painter = painterResource(Res.drawable.derpdroid),
                contentDescription = "Derpdroid"
            )
            LaunchedEffect(showDerpdroid.currentState) {
                if (showDerpdroid.isIdle) {
                    delay(300)
                    showDerpdroid.targetState = false
                }
            }
        }
    }
}

@Composable
private fun BiographyCardContent(onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(0.7f), onClick = onClick) {
        Row(modifier = Modifier.padding(Dimens.medium)) {
            AsyncImage(
                modifier = Modifier
                    .size(profileImageSize)
                    .clip(CircleShape),
                model = ImageUrls.PROFILE.url,
                contentDescription = null,
            )
            HorizontalSpacer(Dimens.small)
            Column {
                Text(text = "Jason Toms", style = MaterialTheme.typography.headlineMedium)
                SocialLinks()
                Text(text = stringResource(Res.string.bio), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun SocialLinks() {
    val uriHandler = LocalUriHandler.current
    Row {
        IconButton(onClick = { uriHandler.openUri("https://www.linkedin.com/in/jasonctoms/") }) {
            Image(
                painter = painterResource(Res.drawable.ic_linkedin),
                contentDescription = "LinkedIn"
            )
        }
        IconButton(onClick = { uriHandler.openUri("https://github.com/jasonctoms") }) {
            Icon(
                painter = painterResource(Res.drawable.ic_github),
                contentDescription = "GitHub"
            )
        }
    }
}

@Preview
@Composable
private fun BiographyCardPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(Dimens.small)
        ) {
            BiographyCard()
        }
    }
}