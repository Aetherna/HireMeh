package dev.aetherna.hiremeh.common.mvi

import android.database.Observable

interface MviViewModel {
    fun processIntents(intents: Observable<MviIntent>)
    fun states(): Observable<MviViewState>
}