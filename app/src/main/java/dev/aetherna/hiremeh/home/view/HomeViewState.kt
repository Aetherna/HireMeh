package dev.aetherna.hiremeh.home.view

import dev.aetherna.hiremeh.common.domain.Post
import dev.aetherna.hiremeh.common.mvi.MviViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
) : MviViewState {

    companion object {
        val idle = HomeViewState(
            false
        )

        val loading = HomeViewState(
            true
        )
    }
}