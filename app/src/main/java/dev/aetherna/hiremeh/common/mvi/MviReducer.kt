package dev.aetherna.hiremeh.common.mvi

import io.reactivex.functions.BiFunction

interface MviReducer<State : MviViewState, Result : MviResult> {
    fun reduce(): BiFunction<State, Result, State>
}