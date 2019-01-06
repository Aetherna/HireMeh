package dev.aetherna.hiremeh.common.mvi

import io.reactivex.ObservableTransformer

interface MviProcessor<Action : MviAction, Result : MviResult> {
    fun process(): ObservableTransformer<Action, Result>
}