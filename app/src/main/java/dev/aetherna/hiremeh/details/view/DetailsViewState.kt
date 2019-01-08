package dev.aetherna.hiremeh.details.view

import dev.aetherna.hiremeh.common.mvi.MviViewState
import dev.aetherna.hiremeh.home.view.PostDetails

data class DetailsViewState(
    val isLoading: Boolean = false,
    val postDetails: PostDetails?,
    val error: String? = null
) : MviViewState {
    companion object {
        val loading = DetailsViewState(true, null)
    }
}