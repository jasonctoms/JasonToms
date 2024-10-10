package ui

sealed interface StartScreenItem {
    data object ProfilePhoto : StartScreenItem
    data object Biography : StartScreenItem
    data class Project(val project: AndroidProject) : StartScreenItem
    data object Footer : StartScreenItem
}