package start

import androidx.lifecycle.ViewModel
import getPlatform
import utils.ViewState

class StartScreenViewModel : ViewModel() {
    val viewState = ViewState(StartScreenViewState())
    private val platform = getPlatform()

    init {
        viewState.update { copy(greeting = "Thanks for testing this out on ${platform.name}!") }
    }

    fun togglePicture() {
        viewState.update { copy(showImage = !showImage) }
    }
}

data class StartScreenViewState(
    val showImage: Boolean = false,
    val greeting: String = "",
)