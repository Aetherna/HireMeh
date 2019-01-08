package dev.aetherna.hiremeh.home.model

import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.home.view.HomeViewState
import io.reactivex.functions.BiFunction

class HomeReducer : MviReducer<HomeViewState, HomeResult> {
    override fun reduce() = BiFunction<HomeViewState, HomeResult, HomeViewState> { previous, result ->
        when (result) {
            is HomeResult.DataLoading -> previous.copy(isLoading = true)
            is HomeResult.DataLoaded -> HomeViewState(isLoading = false, posts = result.posts)
            is HomeResult.DataLoadingError -> previous.copy(
                isLoading = false,
                error = result.errorMessage
            ) //todo message could be provided from resource provider for the sake of translations
        }
    }
}