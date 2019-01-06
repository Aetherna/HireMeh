package dev.aetherna.hiremeh.home.view

import dev.aetherna.hiremeh.common.mvi.MviViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val buttonName: String
) : MviViewState {

    companion object {
        val idle = HomeViewState(false, "Doing nuffin'")
    }
}