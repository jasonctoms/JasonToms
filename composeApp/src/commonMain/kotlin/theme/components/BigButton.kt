package ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import jasontoms.composeapp.generated.resources.Res
import jasontoms.composeapp.generated.resources.ic_arrow_forward
import org.jetbrains.compose.resources.painterResource
import theme.Dimens
import theme.components.HorizontalSpacer

@Composable
fun BigButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .heightIn(min = 56.dp)
            .pointerHoverIcon(PointerIcon.Hand),
        onClick = onClick,
        contentPadding = PaddingValues(start = Dimens.medium, end = Dimens.small + Dimens.xxSmall),
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
        HorizontalSpacer(Dimens.xSmall)
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize + 2.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = null,
        )
    }
}
