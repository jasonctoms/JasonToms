import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import navigation.Navigator
import theme.AppTheme
import ui.SiteScaffold

@Composable
fun App(navigator: Navigator = remember { Navigator() }) {
    AppTheme {
        SiteScaffold(navigator = navigator)
    }
}
