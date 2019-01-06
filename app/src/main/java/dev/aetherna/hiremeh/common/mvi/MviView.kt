package dev.aetherna.hiremeh.common.mvi

import io.reactivex.Observable

interface MviView {
    fun intents(): Observable<MviIntent>
    fun render(state: MviViewState)
}