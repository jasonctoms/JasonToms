package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * A class to handle one-shot events from a ViewModel back to a composable screen. Used for things like
 * navigation or triggering things like animations or scroll events. Uses a channel to ensure a hot stream
 * with a single observer where events are guaranteed to be received once and only once.
 *
 * T should be a sealed class or interface that defines your view effects.
 *
 * Best used in conjunction with the ViewEffects composable.
 */
class ViewEffects<T> {
    private val effects = Channel<T>()
    suspend fun collect(block: suspend (T) -> Unit) {
        effects.receiveAsFlow().collect { block(it) }
    }

    context(vm: ViewModel)
    fun send(effect: T) {
        vm.viewModelScope.launch {
            effects.send(effect)
        }
    }
}

/**
 * Composable to collect events from the ViewEffects class. Usually used at the top of a screen.
 */
@Composable
fun <T> ViewEffects(viewEffects: ViewEffects<T>, block: suspend CoroutineScope.(T) -> Unit) {
    LaunchedEffect(Unit) {
        viewEffects.collect { block(it) }
    }
}
