package dev.aetherna.hiremeh.home

import dev.aetherna.hiremeh.common.mvi.MviReducer
import dev.aetherna.hiremeh.home.view.HomeViewState
import io.reactivex.functions.BiFunction

class HomeReducer : MviReducer<HomeViewState, HomeResult> {
    override fun reduce() = BiFunction<HomeViewState, HomeResult, HomeViewState> { previous, result ->
        previous
    }

}