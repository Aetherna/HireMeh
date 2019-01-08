package dev.aetherna.hiremeh.details.model

import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.details.view.DetailsViewState
import io.reactivex.functions.BiFunction

class DetailsReducer : MviReducer<DetailsViewState, DetailsResult> {

    override fun reduce() = BiFunction<DetailsViewState, DetailsResult, DetailsViewState> { previous, result ->
        when (result) {
            is DetailsResult.Success -> previous.copy(isLoading = false, postDetails = result.postDetails)
            is DetailsResult.Error -> DetailsViewState(false, null, error = result.message)
        }
    }
}