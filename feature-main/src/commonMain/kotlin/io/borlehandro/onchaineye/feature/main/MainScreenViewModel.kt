package io.borlehandro.onchaineye.feature.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel {

    private val _state = MutableStateFlow(MainScreenState(selectedTab = 0))
    val state = _state.asStateFlow()

    // TODO: Migrate to UiEvent-s
    fun onTabSelected(tabIndex: Int) {
        _state.update { MainScreenState(selectedTab = tabIndex) }
    }
}