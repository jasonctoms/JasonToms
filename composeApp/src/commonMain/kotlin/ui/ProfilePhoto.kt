package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import coil3.compose.AsyncImage
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.derpdroid
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.ic_linkedin
import org.jetbrains.compose.resources.painterResource
import theme.Dimens
import theme.components.FlippableCard
import utils.ImageUrls
import utils.VerticalSpacer

@Composable
fun ProfilePhoto(modifier: Modifier = Modifier) {
    FlippableCard(
        cardModifier = modifier,
        frontCardContent = { ProfilePhotoCardFront() },
        backCardContent = { ProfilePhotoCardBack() },
    )
}

@Composable
private fun ProfilePhotoCardFront() {
    Column(
        modifier = Modifier.padding(Dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(profileImageSize)
                .clip(CircleShape),
            model = ImageUrls.PROFILE.url,
            contentDescription = null,
        )
        VerticalSpacer(Dimens.small)
        Text(text = "Jason Toms", style = MaterialTheme.typography.headlineMedium)
        SocialLinks()
    }
}

@Composable
private fun ProfilePhotoCardBack() {
    Box(
        modifier = Modifier.padding(Dimens.medium)
    ) {
        Image(
            modifier = Modifier.size(profileImageSize),
            painter = painterResource(Res.drawable.derpdroid),
            contentDescription = "Derpdroid"
        )
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