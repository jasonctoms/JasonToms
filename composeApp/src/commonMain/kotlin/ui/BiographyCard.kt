package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.bio
import jasontoms.composeapp.generated.resources.derpdroid
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.ic_linkedin
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.Dimens
import theme.components.CARD_FLIP_DURATION
import theme.components.FlippableCard
import utils.HorizontalSpacer
import utils.ImageUrls

val profileImageSize = 200.dp

@Composable
fun BiographyCard(modifier: Modifier = Modifier) {
    var derpdroidEntering by remember { mutableStateOf(false) }
    FlippableCard(
        cardModifier = modifier,
        frontCardContent = { BiographyCardFront() },
        backCardContent = { BiographyCardBack(derpdroidEntering) },
        cardFlipping = { derpdroidEntering = !it }
    )
}

@Composable
private fun BiographyCardFront() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
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

@Composable
private fun BiographyCardBack(derpdroidEntering: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
        // TODO: figure out why the enter animation isn't working
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth(),
            visible = derpdroidEntering,
            enter = slideInHorizontally(
                animationSpec = tween(durationMillis = 300, delayMillis = CARD_FLIP_DURATION / 2),
            ),
            exit = slideOutHorizontally(
                animationSpec = tween(durationMillis = 300),
                targetOffsetX = { it / 2 }
            ),
        ) {
            Image(
                modifier = Modifier.size(profileImageSize),
                painter = painterResource(Res.drawable.derpdroid),
                contentDescription = "Derpdroid"
            )
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