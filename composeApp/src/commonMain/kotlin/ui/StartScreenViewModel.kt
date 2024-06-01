package ui

import androidx.lifecycle.ViewModel
import utils.ViewState

class StartScreenViewModel : ViewModel() {
    // a ViewModel isn't really necessary yet, but this might get more complicated in the future

    val viewState = ViewState(StartScreenViewState())

    init {
        buildStartScreenList()
    }

    private fun buildStartScreenList() {
        val list = buildList<StartScreenItem> {
            add(StartScreenItem.Biography)
        }
        viewState.update { copy(items = list) }
    }
}

data class StartScreenViewState(
    val items: List<StartScreenItem> = listOf(
        StartScreenItem.Biography,
        StartScreenItem.Footer,
    )
)