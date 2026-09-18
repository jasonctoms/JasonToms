package theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.ic_github
import jasontoms.composeapp.generated.resources.ic_linkedin
import jasontoms.composeapp.generated.resources.ic_sessionize
import org.jetbrains.compose.resources.painterResource

@Composable
fun SocialLinks(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        IconButton(onClick = { uriHandler.openUri("https://www.linkedin.com/in/jasonctoms/") }) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_linkedin),
                contentDescription = "LinkedIn"
            )
        }
        IconButton(onClick = { uriHandler.openUri("https://sessionize.com/jason-toms/") }) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_sessionize),
                contentDescription = "Sessionize"
            )
        }
        IconButton(onClick = { uriHandler.openUri("https://github.com/jasonctoms") }) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_github),
                contentDescription = "GitHub",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
