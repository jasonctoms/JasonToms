package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * A class to abstract away some boilerplate to handle a view state for composable screens.
 *
 * T should be a data class that describes your view state. Whenever you need to update any part
 * of the state, you can call update from inside of a ViewModel. Normally you would want to use
 * the copy function to update the state.
 */
class ViewState<T>(initialState: T) {
    private val _state = MutableStateFlow(initialState)

    /**
     * The current view state.
     */
    val currentState get() = _state.value

    /**
     * Update the view state, most likely by using copy() on the current state.
     *
     * There is a context receiver on this function to ensure it can only be called from a ViewModel.
     */
    context(ViewModel)
    fun update(function: T.() -> T) {
        _state.update(function)
    }

    /**
     * Collect the view state at the screen-level of your composable, and pass the values
     * individually to your components.
     */
    @Composable
    fun asState(): State<T> = _state.collectAsState()
}
